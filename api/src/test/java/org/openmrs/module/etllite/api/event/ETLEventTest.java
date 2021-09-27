package org.openmrs.module.etllite.api.event;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.etllite.api.constants.Constants;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ETLEventTest {

    private static final String TEST_SUBJECT = "testSubject";
    private static final String TEST_JOB_ID = "jobId";

    private ETLEvent etlEvent;

    @Before
    public void setUp() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Constants.PARAM_JOB_ID, TEST_JOB_ID);

        etlEvent = new ETLEvent("testSubject", parameters);
    }

    @Test
    public void shouldProperlyGetJobIdAsString() {
        assertThat(etlEvent.getJobId(), equalTo(TEST_JOB_ID));
    }

    @Test
    public void shouldProperlyGenerateTaskName() {
        assertThat(etlEvent.generateTaskName(), equalTo(TEST_SUBJECT + "-" + TEST_JOB_ID));
    }

    @Test
    public void shouldProperlyConvertProperties() {
        Map<String, String> expected = new HashMap<>();
        expected.put(Constants.PARAM_JOB_ID, TEST_JOB_ID);
        assertThat(etlEvent.convertProperties(), equalTo(expected));
    }
}
