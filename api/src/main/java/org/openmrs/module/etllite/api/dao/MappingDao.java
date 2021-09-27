package org.openmrs.module.etllite.api.dao;

import org.openmrs.api.db.OpenmrsMetadataDAO;
import org.openmrs.module.etllite.api.domain.Mapping;

import java.util.List;

/**
 * The MappingDao class. This is DAO for Mapping entity.
 */
public interface MappingDao extends OpenmrsMetadataDAO<Mapping> {

    /**
     * Fetches ETL Mapping details by name and ETL source
     *
     * @param name   mapping name
     * @param source ETL source
     * @return <code>Mapping</code> by name, null if the specified mapping not found
     */
    Mapping findByNameAndSource(String name, String source);

    /**
     * Fetches ETL Mapping details by source
     *
     * @param source source system to extract the data
     * @return list of mappings by name, null if there is no matching name
     */

    List<Mapping> findBySource(String source);

    Mapping create(Mapping mapping);

    Mapping findById(Integer id);

    Mapping update(Mapping mapping);

    List<Mapping> retrieveAll();

    void deleteAll();
}
