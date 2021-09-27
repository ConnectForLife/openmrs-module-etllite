package org.openmrs.module.etllite.api.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Util Class
 *
 * @author nanakapa
 */
public class Util {

    private static final int DEFAULT_FETCH_SIZE = 1000;

    /**
     * This method creates <code>NamedParameterJdbcTemplate</code> using the datasource
     *
     * @param dataSource <code>DataSource</code>
     * @param fetchSize  parameter to retrieve the data in chunks
     * @return <code>NamedParameterJdbcTemplate</code>
     */
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource, int fetchSize) {
        if (null == dataSource) {
            throw new IllegalArgumentException("datasource can not be null");
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setFetchSize(fetchSize != 0 ? fetchSize : DEFAULT_FETCH_SIZE);
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    /**
     * This method converts comma separated string to Map
     *
     * @param string comma separated string
     * @return <code>Map</code>
     */
    public Map<String, String> parseStringToMap(String string) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(string)) {
            return map;
        }
        String[] strings = string.split("\\s*,\\s*");
        for (String s : strings) {
            String[] kv = s.split("\\s*:\\s*");
            if (kv.length == 2) {
                map.put(kv[0], kv[1]);
            } else {
                throw new IllegalArgumentException(String.format("%s is an invalid map", string));
            }
        }
        return map;
    }

    public String getEnvironmentProperty(String property) {
        return System.getenv(property);
    }

    public String getSystemProperty(String property) {
        return System.getProperty(property);
    }

    public Properties getProperties(String path) throws IOException {
        Properties properties = new Properties();

        if (StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Error in loading encryption config system variable");
        }

        try (InputStream in = new FileInputStream(path)) {
            properties.load(in);
        }
        return properties;
    }
}
