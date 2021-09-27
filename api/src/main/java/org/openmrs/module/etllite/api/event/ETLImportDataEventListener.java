package org.openmrs.module.etllite.api.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.etllite.api.constants.Constants;
import org.openmrs.module.etllite.api.service.ETLService;

import java.util.List;
import java.util.Map;

public class ETLImportDataEventListener extends AbstractETLEventListener {

    private static final Log LOGGER = LogFactory.getLog(ETLImportDataEventListener.class);

    private ETLService etlService;

    public void setEtlService(ETLService etlService) {
        this.etlService = etlService;
    }

    @Override
    public String getSubject() {
        return Constants.ETL_EVENT_IMPORT_DATA;
    }

    @Override
    protected void handleEvent(Map<String, Object> properties) {
        LOGGER.debug("ETL Handler method invoked");

        List<String> mappings = (List<String>) properties.get(Constants.PARAM_MAPPINGS);
        for (String mapping : mappings) {
            LOGGER.debug(String.format("ETL started for mapping : %s", mapping));
            etlService.doETL(mapping, properties);
        }
    }
}
