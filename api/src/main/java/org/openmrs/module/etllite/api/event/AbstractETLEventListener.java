package org.openmrs.module.etllite.api.event;

import org.openmrs.api.context.Daemon;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.etllite.api.exception.ETLRuntimeException;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractETLEventListener implements ETLEventListener {

    private DaemonToken daemonToken;

    @Override
    public void onMessage(Message message) {
        try {
            final Map<String, Object> properties = getProperties(message);
            Daemon.runInDaemonThread(new Runnable() {
                @Override
                public void run() {
                    handleEvent(properties);
                }
            }, daemonToken);
        } catch (JMSException ex) {
            throw new ETLRuntimeException("Error during handling ETL event", ex);
        }
    }

    @Override
    public void setDaemonToken(DaemonToken daemonToken) {
        this.daemonToken = daemonToken;
    }

    protected abstract void handleEvent(Map<String, Object> properties);

    private Map<String, Object> getProperties(Message message) throws JMSException {
        Map<String, Object> properties = new HashMap<>();

        // OpenMRS event module uses underneath MapMessage to construct Message. For some reason retrieving properties
        // from Message interface doesn't work and we have to map object to MapMessage.
        MapMessage mapMessage = (MapMessage) message;
        Enumeration<String> propertiesKey = (Enumeration<String>) mapMessage.getMapNames();

        while (propertiesKey.hasMoreElements()) {
            String key = propertiesKey.nextElement();
            properties.put(key, mapMessage.getObject(key));
        }
        return properties;
    }
}
