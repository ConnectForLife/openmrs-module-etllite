package org.openmrs.module.etllite.api.event;

import org.openmrs.event.EventListener;
import org.openmrs.module.DaemonToken;

public interface ETLEventListener extends EventListener {

    String getSubject();

    void setDaemonToken(DaemonToken daemonToken);
}
