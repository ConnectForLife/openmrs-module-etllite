package org.openmrs.module.etllite.api.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.etllite.api.constants.Constants;

import java.util.Map;

public class ETLFailureEventListener extends AbstractETLEventListener {

    private static final Log LOGGER = LogFactory.getLog(ETLFailureEventListener.class);

    @Override
    public String getSubject() {
        return Constants.ETL_FAILURE_SUBJECT;
    }

    @Override
    protected void handleEvent(Map<String, Object> properties) {
        LOGGER.debug("ETL Failure handler invoked");
        Context.getRegisteredComponent("etllite.ETLErrorPersistence", ETLErrorPersistence.class)
                .persistError(properties);
    }
}
