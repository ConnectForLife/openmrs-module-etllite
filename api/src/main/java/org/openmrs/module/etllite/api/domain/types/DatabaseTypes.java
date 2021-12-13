package org.openmrs.module.etllite.api.domain.types;

/**
 * Database types.
 *
 * @author nanakapa
 */
public enum DatabaseTypes {

    MYSQL("com.mysql.jdbc.Driver"),

    MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDriver"),

    POSTGRESQL("org.postgresql.Driver");

    private final String name;

    DatabaseTypes(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
