package org.openmrs.module.etllite.api.service;

import org.openmrs.module.etllite.api.domain.VisitErrorLog;

import java.util.Date;
import java.util.List;

public interface ETLErrorService {
    List<VisitErrorLog> getVisitErrorLogs(String databaseName, String mappingName, Date startDate, Date endDate);
}
