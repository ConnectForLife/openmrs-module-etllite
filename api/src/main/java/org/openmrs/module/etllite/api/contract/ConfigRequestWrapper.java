package org.openmrs.module.etllite.api.contract;

import org.openmrs.module.etllite.api.domain.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Contract for ETL databases and spring services
 *
 * @author nanakapa
 */

public class ConfigRequestWrapper {

    private List<Config> databases = new ArrayList<>();

    private String services;

    public List<Config> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Config> databases) {
        this.databases = databases;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
