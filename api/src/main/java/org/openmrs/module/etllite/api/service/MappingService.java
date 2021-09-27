package org.openmrs.module.etllite.api.service;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.etllite.api.domain.Mapping;
import org.openmrs.module.etllite.api.exception.ETLException;
import org.openmrs.module.etllite.api.exception.MappingAlreadyExistsException;
import org.openmrs.module.etllite.api.exception.MappingNotFoundException;
import org.openmrs.module.etllite.api.util.PrivilegeConstants;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Defines APIs to add, update, delete and extract ETL mapping details. Mapping contains the queries
 * to extract the data and velocity templates to transform and load the data
 *
 * @author nanakapa
 */
public interface MappingService extends OpenmrsService {

    /**
     * Creates a new ETL mapping <b>and schedules the Task to execute it.</b>
     *
     * @param mapping ETL mapping to be created
     * @return New mapping entity
     * @throws MappingAlreadyExistsException if the mapping already exists in database with the
     *                                       name and source
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    Mapping create(Mapping mapping) throws MappingAlreadyExistsException;

    /**
     * Save the current state of {@code mapping}. Creates a new ETL mapping or updates existing.
     * <p>
     * This method complies with default OpenMRS entity service method names pattern and is required.
     * </p>
     *
     * @param mapping the mapping to save, not null
     * @return the saved Mapping, never null
     * @throws MappingAlreadyExistsException if {@code mapping} has no ID set (not managed)
     *                                       and there is already a mapping with given name and source
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    Mapping saveMapping(Mapping mapping) throws MappingAlreadyExistsException, MappingNotFoundException;

    /**
     * Updates the ETL mapping <b>and schedules (or unschedules if needed) the Task to execute it.</b>
     *
     * @param mapping ETL mapping to be updated
     * @return updated mapping entity
     * @throws MappingNotFoundException if the mapping does not exists in database
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    Mapping update(Mapping mapping) throws MappingNotFoundException;

    /**
     * Deletes the specific mapping by mapping id <b>and unschedules the Task to execute it.</b>
     *
     * @param id mapping id to be deleted
     * @throws MappingNotFoundException if the mapping does not exists in database
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    void delete(Integer id) throws MappingNotFoundException;

    /**
     * Fetches the ETL mapping by id
     *
     * @param id mapping id
     * @return Mapping entity for the specified mapping id
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    Mapping findById(Integer id);

    /**
     * Fetches list of mappings tagged to a ETL database
     *
     * @param source name of the ETL database (e.g. ICEA)
     * @return list of mappings
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    List<Mapping> findBySource(String source);

    /**
     * Fetches all mappings
     *
     * @return list of mappings
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    List<Mapping> findAll();

    /**
     * Fetches extracted and transformed data for the specified mapping id
     *
     * @param id mapping id
     * @return test data results for the specific mapping
     * @throws ETLException
     */
    @Authorized(PrivilegeConstants.ETL_MAPPINGS_PRIVILEGE)
    Map<String, List<Map<String, Object>>> testMapping(Integer id) throws ETLException, MappingNotFoundException, IOException;
}
