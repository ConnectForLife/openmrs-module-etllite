package org.openmrs.module.etllite.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * ETL Error Log stores information about the failed imported records
 *
 * @author nanakapa
 */

@Entity(name = "etl.ErrorLog")
@Table(name = "etl_error_logs")
public class ErrorLog extends AbstractBaseOpenmrsData {

    @Id
    @GeneratedValue
    @Column(name = "etl_error_logs_id")
    private Integer id;

    @Column(nullable = false)
    private String databaseName;

    @Column(nullable = false)
    private String mapping;

    @Column(nullable = false)
    private String sourceKey;

    @Column(nullable = false)
    private String sourceValue;

    @Column(nullable = false)
    private Date runOn;

    /**
     * String representation of exception
     */
    @Column(columnDefinition = "TEXT")
    private String stackTrace;

    /**
     * Information about the exception. Currently has class name and method name where the exception occurred
     */
    @Column
    private String errorMessage;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public Date getRunOn() {
        return runOn;
    }

    public void setRunOn(Date runOn) {
        this.runOn = runOn;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ErrorLog failedLog = (ErrorLog) o;

        return Objects.equals(this.id, failedLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ErrorLog{" + "id=" + id + '}';
    }
}

