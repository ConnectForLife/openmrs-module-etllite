package org.openmrs.module.etllite.api.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.etllite.api.builder.ConfigBuilder;
import org.openmrs.module.etllite.api.constants.Constants;
import org.openmrs.module.etllite.api.contract.ConfigRequestWrapper;
import org.openmrs.module.etllite.api.domain.Config;
import org.openmrs.module.etllite.api.domain.types.DatabaseTypes;
import org.openmrs.module.etllite.api.service.ConfigService;
import org.openmrs.module.etllite.api.service.SettingsManagerService;
import org.openmrs.module.etllite.api.util.ResourceUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Config Service implementation for etl-lite module, adapted from IVR module
 *
 * @author nanakapa
 */
public class ConfigServiceImpl extends BaseOpenmrsService implements ConfigService {

    private static final String ERROR_MESSAGE = "Database configuration does not exists with this id";

    private static final Log LOGGER = LogFactory.getLog(ConfigServiceImpl.class);

    //Manages the list of ETL database settings
    private Map<String, Config> configs = new HashMap<>();

    // Manages the list of spring services
    private String springServices;

    // Manages the list of data sources for the ETL databases
    private Map<String, DriverManagerDataSource> dataSources = new HashMap<>();

    private SettingsManagerService settingsManagerService;

    private ConfigBuilder configBuilder;

    public void initialize() {
        try {
            loadConfigs();
            setUpDataSources();
        } catch (Exception ex) {
            LOGGER.error(Constants.CONFIG_SERVICE_INITIALIZATION_ERROR, ex);
        }
    }

    public void setSettingsManagerService(SettingsManagerService settingsManagerService) {
        this.settingsManagerService = settingsManagerService;
    }

    public void setConfigBuilder(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
    }

    @Override
    public Config getConfig(String name) {
        if (configs.containsKey(name)) {
            return configs.get(name);
        }
        String message = String.format("Unknown config: '%s'.", name);
        throw new IllegalArgumentException(message);
    }

    @Override
    public List<Config> allConfigs() {
        return new ArrayList<Config>(configs.values());
    }

    @Override
    public boolean hasConfig(String name) {
        return configs.containsKey(name);
    }

    @Override
    public void createOrUpdateConfigs(ConfigRequestWrapper configRequestWrapper) {
        validateConfigs(configRequestWrapper.getDatabases());
        List<Config> databases = getDatabasesFromConfigRequestWrapperAndValidate(configRequestWrapper.getDatabases());
        configRequestWrapper.setDatabases(databases);

        Gson gson = new Gson();
        String jsonText = gson.toJson(configRequestWrapper);

        ByteArrayResource resource = new ByteArrayResource(jsonText.getBytes());
        settingsManagerService.saveRawConfig(Constants.CONFIG_FILE_NAME, resource);

        loadConfigs();
        setUpDataSources();
    }

    @Override
    public void deleteConfig(String name) {
        if (hasConfig(name)) {
            configs.remove(name);
        }
    }

    @Override
    public void deleteAll() {
        if (!configs.isEmpty()) {
            configs.clear();
        }
    }

    @Override
    public DataSource getDataSource(String name) {
        return dataSources.get(name);
    }

    @Override
    public boolean testDatabase(String name) {
        Config database = getConfig(name);
        DriverManagerDataSource basicDataSource = dataSources.get(database.getName());

        if (null == basicDataSource) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = basicDataSource.getConnection();
            callableStatement = connection.prepareCall(database.getQuery());
            return callableStatement.execute();
        } catch (SQLException sqlException) {
            LOGGER.error("Connection error", sqlException);
        } finally {
            try {
                if (null != callableStatement) {
                    callableStatement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Error in closing the connection object", e);
            }
        }
        return false;
    }

    @Override
    public String getServices() {
        return this.springServices;
    }

    private synchronized void loadConfigs() {
        ConfigRequestWrapper configRequestWrapper;

        if (configurationNotExist()) {
            loadDefaultETLConfiguration();
        }

        try (InputStream is = settingsManagerService.getRawConfig(Constants.CONFIG_FILE_NAME)) {
            String jsonText = IOUtils.toString(is);
            LOGGER.debug(String.format("Loading %s", Constants.CONFIG_FILE_NAME));
            Gson gson = new Gson();
            configRequestWrapper = gson.fromJson(jsonText, new TypeToken<ConfigRequestWrapper>() {
            } .getType());
        } catch (Exception e) {
            String message = String.format("There seems to be a problem with the json text in %s: %s", Constants.CONFIG_FILE_NAME, e.getMessage());
            throw new JsonIOException(message, e);
        }

        for (Config database : configRequestWrapper.getDatabases()) {
            configs.put(database.getName(), database);
        }

        springServices = configRequestWrapper.getServices();
    }

    private boolean configurationNotExist() {
        return !settingsManagerService.configurationExist(Constants.CONFIG_FILE_NAME);
    }

    private void loadDefaultETLConfiguration() {
        String defaultConfiguration = ResourceUtil.readResourceFile(Constants.CONFIG_FILE_NAME);
        ByteArrayResource resource = new ByteArrayResource(defaultConfiguration.getBytes());
        settingsManagerService.saveRawConfig(Constants.CONFIG_FILE_NAME, resource);
    }

    /**
     * Reload the datasources Map during application load/add/update/delete database configurations
     *
     * @return datasources Map
     */
    private Map<String, DriverManagerDataSource> setUpDataSources() {
        List<Config> configList = new ArrayList<Config>(configs.values());
        for (Config database : configList) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(DatabaseTypes.valueOf(database.getType()).toString());
            dataSource.setUrl(database.getUrl());
            dataSource.setUsername(database.getUser());
            dataSource.setPassword(configBuilder.decryptPassword(database.getDbPassword()));

            dataSources.put(database.getName(), dataSource);
        }
        return dataSources;
    }

    private List<Config> getDatabasesFromConfigRequestWrapperAndValidate(List<Config> configs) {
        List<Config> newConfigs = new ArrayList<>();
        Config existingDBConfig;
        for (Config config : configs) {
            if (hasConfig(config.getName())) {
                existingDBConfig = getConfig(config.getName());
            } else {
                existingDBConfig = new Config();
            }
            newConfigs.add(configBuilder.createConfig(config, existingDBConfig));
        }
        return newConfigs;
    }

    private void validateConfigs(List<Config> configs) {
        for (Config config : configs) {
            if (hasConfig(config.getName())) {
                config.validateUpdate();
            } else {
                config.validateSave();
            }
        }
    }
}
