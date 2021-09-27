package org.openmrs.module.etllite.api.builder;

import org.openmrs.module.etllite.api.contract.ConfigResponse;
import org.openmrs.module.etllite.api.domain.Config;

/**
 * Response Builder for Database settings
 *
 * @author nanakapa
 */
public class ConfigResponseBuilder {

    /**
     * Config response builder
     *
     * @param config ETL database settings
     * @return <code>ConfigResponse</code>
     */
    public ConfigResponse createFrom(Config config) {
        ConfigResponse configResponse = new ConfigResponse();
        configResponse.setName(config.getName());
        configResponse.setType(config.getType());
        configResponse.setUser(config.getUser());
        configResponse.setQuery(config.getQuery());
        configResponse.setUrl(config.getUrl());
        return configResponse;
    }
}
