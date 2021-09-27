package org.openmrs.module.etllite.api.event;

import java.util.Map;

public interface ETLErrorPersistence {

    void persistError(Map<String, Object> properties);
}
