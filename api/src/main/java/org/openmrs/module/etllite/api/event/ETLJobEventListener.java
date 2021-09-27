package org.openmrs.module.etllite.api.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.etllite.api.constants.Constants;
import org.openmrs.module.etllite.api.service.ETLService;

import java.util.Map;

public class ETLJobEventListener extends AbstractETLEventListener {

    private static final Log LOGGER = LogFactory.getLog(ETLJobEventListener.class);

    private ETLService etlService;

    public void setEtlService(ETLService etlService) {
        this.etlService = etlService;
    }

    @Override
    public String getSubject() {
        return Constants.SUBJECT_RUNNER;
    }

    @Override
    protected void handleEvent(Map<String, Object> properties) {
        LOGGER.debug("ETL Batch Job started");

        String mapping = (String) properties.get(Constants.PARAM_MAPPING);

        LOGGER.debug(String.format("ETL started for mapping : %s", mapping));
        etlService.doETL(mapping, properties);
    }
}
