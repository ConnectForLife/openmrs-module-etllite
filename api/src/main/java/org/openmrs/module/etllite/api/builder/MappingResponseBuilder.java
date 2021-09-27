package org.openmrs.module.etllite.api.builder;

import org.openmrs.module.etllite.api.contract.MappingResponse;
import org.openmrs.module.etllite.api.domain.Mapping;

/**
 * Response Builder class for Mappings
 *
 * @author nanakapa
 */

public class MappingResponseBuilder {

    /**
     * Creates <code>MappingResponse</code> from mapping entity
     *
     * @param mapping Mapping entity
     * @return <code>MappingResponse</code>
     */
    public MappingResponse createFrom(Mapping mapping) {
        MappingResponse mappingResponse = new MappingResponse();
        mappingResponse.setId(mapping.getId());
        mappingResponse.setName(mapping.getName());
        mappingResponse.setSource(mapping.getSource());
        mappingResponse.setQuery(mapping.getQuery());
        mappingResponse.setTransformTemplate(mapping.getTransformTemplate());
        mappingResponse.setLoadTemplate(mapping.getLoadTemplate());
        mappingResponse.setCronExpression(mapping.getCronExpression());
        mappingResponse.setFetchSize(mapping.getFetchSize());
        mappingResponse.setTestResultsSize(mapping.getTestResultsSize());
        return mappingResponse;
    }
}
