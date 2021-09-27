package org.openmrs.module.etllite.api.dao;

import org.openmrs.api.db.OpenmrsDataDAO;
import org.openmrs.module.etllite.api.domain.ErrorLog;

import java.util.Date;
import java.util.List;

public interface ErrorLogDao extends OpenmrsDataDAO<ErrorLog> {

    /**
     * Find the error log details for the specified ETL source, mapping,job run date, source key and source values
     *
     * @param database    ETL source
     * @param mapping     mapping name
     * @param sourceKey   key name to identify the source record
     * @param sourceValue key value to identify the source record
     * @param runOn       job run date
     * @return error log details
     */

    ErrorLog findBySourceKeyAndRunDate(String database, String mapping, String sourceKey, String sourceValue, Date runOn);

    /**
     * Find the error log details for the specified ETL source, mapping and job run date
     *
     * @param database
     * @param mapping
     * @param runOn
     * @return list of error logs
     */

    List<ErrorLog> findByMappingAndRunDate(String database, String mapping, Date runOn);

    List<ErrorLog> findByMappingAndBetweenRunDates(String database, String mapping, Date startDate, Date endDate);

    ErrorLog create(ErrorLog errorLog);

}
