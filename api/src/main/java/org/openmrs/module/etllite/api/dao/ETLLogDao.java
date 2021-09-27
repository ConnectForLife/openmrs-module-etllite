package org.openmrs.module.etllite.api.dao;

import org.openmrs.api.db.OpenmrsDataDAO;
import org.openmrs.module.etllite.api.domain.ETLLog;

import java.util.Date;
import java.util.List;

public interface ETLLogDao extends OpenmrsDataDAO<ETLLog> {
    ETLLog findById(Integer jobId);

    ETLLog update(ETLLog etlLog);

    ETLLog create(ETLLog etlLog);

    List<ETLLog> retrieveAll();

    void deleteAll();

    Date executeQuery(String database, String mapping);
}
