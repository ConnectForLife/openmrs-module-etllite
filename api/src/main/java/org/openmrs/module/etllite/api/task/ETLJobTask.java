package org.openmrs.module.etllite.api.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.etllite.api.constants.Constants;
import org.openmrs.module.etllite.api.service.ETLService;

import java.util.HashMap;
import java.util.Map;

public class ETLJobTask extends AbstractCronTask {

    private static final Log LOGGER = LogFactory.getLog(ETLJobTask.class);

    @Override
    protected void executeTask() {
        LOGGER.debug("ETL Batch Job started");

        Map<String, Object> properties = new HashMap<>();
        properties.putAll(getTaskDefinition().getProperties());

        String mapping = (String) properties.get(Constants.PARAM_MAPPING);

        LOGGER.debug(String.format("ETL started for mapping : %s", mapping));
        Context.getRegisteredComponent("etllite.etlService", ETLService.class).doETL(mapping, properties);
    }
}
