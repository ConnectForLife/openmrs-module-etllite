package org.openmrs.module.etllite.api.contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Response Contract for ETL databases and spring services
 *
 * @author nanakapa
 */

public class ConfigResponseWrapper {

    private List<ConfigResponse> databases = new ArrayList<>();

    private String services;

    public List<ConfigResponse> getDatabases() {
        return databases;
    }

    public void setDatabases(List<ConfigResponse> databases) {
        this.databases = databases;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
