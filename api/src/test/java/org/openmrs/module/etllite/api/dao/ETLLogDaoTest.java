package org.openmrs.module.etllite.api.dao;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.etllite.api.domain.ETLLog;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ETLLogDaoTest extends BaseModuleContextSensitiveTest {

    private static final String DB = "db";
    private static final String MAPPING = "mapping";
    private static final Date RUN_ON = new Date(1587725134741L);

    @Autowired
    private ETLLogDao etlLogDao;

    @Before
    public void setUp() {
        createETLLog();
        createETLLog();
    }

    @Test
    public void shouldFindById() {
        ETLLog etlLog = createETLLog();

        assertNotNull(etlLogDao.findById(etlLog.getId()));
    }

    @Test
    public void shouldCreateETLLog() {
        ETLLog etlLog = createETLLog();

        assertNotNull(etlLog);
    }

    @Test
    public void shouldUpdateETLLog() {
        ETLLog etlLog = createETLLog();
        etlLog.setExtractedRecords(2);

        etlLog = etlLogDao.saveOrUpdate(etlLog);

        assertThat(etlLog.getExtractedRecords(), equalTo(2));
    }

    @Test
    public void shouldRetrieveAll() {
        List<ETLLog> etlLogs = etlLogDao.retrieveAll();

        assertThat(etlLogs.size(), equalTo(2));
    }

    @Test
    public void shouldDeleteAll() {
        etlLogDao.deleteAll();

        assertThat(etlLogDao.retrieveAll().size(), equalTo(0));
    }

    @Test
    public void shouldExecuteQuery() {
        Date date = etlLogDao.executeQuery(DB, MAPPING);

        assertNotNull(date);
    }

    private ETLLog createETLLog() {
        ETLLog etlLog = new ETLLog();
        etlLog.setExtractedRecords(1);
        etlLog.setTransformedRecords(1);
        etlLog.setLoadRecords(1);
        etlLog.setDatabaseName(DB);
        etlLog.setMapping(MAPPING);
        etlLog.setRunOn(RUN_ON);

        return etlLogDao.create(etlLog);
    }
}
