package org.openmrs.module.etllite.api.exception;

/**
 * Exception to indicate a mapping already exists if added as a duplicate
 *
 * @author nanakapa
 */
public class MappingAlreadyExistsException extends Exception {

    private static final long serialVersionUID = -3166584319551656877L;

    public MappingAlreadyExistsException(String message) {
        super(message);
    }

}
