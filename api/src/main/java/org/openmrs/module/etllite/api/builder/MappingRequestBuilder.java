package org.openmrs.module.etllite.api.builder;

import org.openmrs.module.etllite.api.contract.MappingRequest;
import org.openmrs.module.etllite.api.domain.Mapping;

/**
 * Request Builder class for Mapping
 *
 * @author nanakapa
 */

public class MappingRequestBuilder {

    /**
     * Creates Mapping entity from <code>MappingRequest</code>
     *
     * @param mappingRequest contains details required to create or update a mapping
     * @return <code>Mapping</code> entity
     */
    public Mapping createFrom(MappingRequest mappingRequest) {
        Mapping mapping = new Mapping();
        mapping.setName(mappingRequest.getName());
        mapping.setSource(mappingRequest.getSource());
        mapping.setTransformTemplate(mappingRequest.getTransformTemplate());
        mapping.setLoadTemplate(mappingRequest.getLoadTemplate());
        mapping.setQuery(mappingRequest.getQuery());
        mapping.setCronExpression(mappingRequest.getCronExpression());
        mapping.setFetchSize(mappingRequest.getFetchSize());
        mapping.setTestResultsSize(mappingRequest.getTestResultsSize());
        return mapping;
    }
}
