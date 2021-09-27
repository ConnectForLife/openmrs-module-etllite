package org.openmrs.module.etllite.handler.metadatasharing;

/**
 * The wrapper for the ETL Services Map String value.
 */
public class ETLServicesConfigWrapper {
    private String value;

    public ETLServicesConfigWrapper() {

    }

    public ETLServicesConfigWrapper(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
