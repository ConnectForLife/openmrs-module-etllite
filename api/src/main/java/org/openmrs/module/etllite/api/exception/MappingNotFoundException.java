package org.openmrs.module.etllite.api.exception;

/**
 * Exception to indicate a mapping does not exists in database
 *
 * @author nanakapa
 */
public class MappingNotFoundException extends Exception {

    private static final long serialVersionUID = -1883965723132793232L;

    public MappingNotFoundException(String message) {
        super(message);
    }

}
