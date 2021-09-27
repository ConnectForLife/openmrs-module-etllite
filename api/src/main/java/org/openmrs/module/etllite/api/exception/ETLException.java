package org.openmrs.module.etllite.api.exception;

/**
 * Exception to indicate a failure in ETL process
 *
 * @author nanakapa
 */
public class ETLException extends Exception {

    private static final long serialVersionUID = 2544603283766476592L;

    public ETLException(String message, Exception e) {
        super(message, e);
    }
}
