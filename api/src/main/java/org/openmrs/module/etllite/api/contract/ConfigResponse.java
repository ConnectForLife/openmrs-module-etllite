package org.openmrs.module.etllite.api.contract;

/**
 * Database settings response contract class. Password field will be excluded in the response
 *
 * @author nanakapa
 */

public class ConfigResponse {

    private String user;

    private String name;

    private String type;

    private String query;

    private String url;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
