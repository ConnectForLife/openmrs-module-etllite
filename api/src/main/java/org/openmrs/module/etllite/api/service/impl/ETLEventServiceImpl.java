package org.openmrs.module.etllite.api.service.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.event.Event;
import org.openmrs.event.EventMessage;
import org.openmrs.module.etllite.api.event.ETLEvent;
import org.openmrs.module.etllite.api.service.ETLEventService;

import java.io.Serializable;
import java.util.Map;

public class ETLEventServiceImpl extends BaseOpenmrsService implements ETLEventService {

    @Override
    public void sendEventMessage(ETLEvent event) {
        Event.fireEvent(event.getSubject(), convertParamsToEventMessage(event.getParameters()));
    }

    private EventMessage convertParamsToEventMessage(Map<String, Object> params) {
        EventMessage eventMessage = new EventMessage();

        for (String key : params.keySet()) {
            eventMessage.put(key, (Serializable) params.get(key));
        }

        return eventMessage;
    }
}
