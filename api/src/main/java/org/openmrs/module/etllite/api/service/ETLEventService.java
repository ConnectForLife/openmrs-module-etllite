package org.openmrs.module.etllite.api.service;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.etllite.api.event.ETLEvent;

public interface ETLEventService extends OpenmrsService {

    void sendEventMessage(ETLEvent event);
}
