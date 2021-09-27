package org.openmrs.module.etllite.api.task;

import org.junit.Test;
import org.openmrs.module.etllite.api.ETLTestHelper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ETLTaskUtilTest {

    @Test
    public void shouldProperlyGenerateTaskName() {
        assertThat(ETLTaskUtil.generateTaskName("subject", "jobId"), equalTo("subject-jobId"));
    }

    @Test
    public void shouldProperlyParseCronExpIfLength6() {
        assertThat(ETLTaskUtil.removeYearsFromCronIfNeeded(
                ETLTestHelper.MAPPING_CRON_WITHOUT_YEARS), equalTo(ETLTestHelper.MAPPING_CRON_WITHOUT_YEARS));
    }

    @Test
    public void shouldProperlyParseCronExpIfLength7() {
        assertThat(ETLTaskUtil.removeYearsFromCronIfNeeded(
                ETLTestHelper.MAPPING_CRON), equalTo(ETLTestHelper.MAPPING_CRON_WITHOUT_YEARS));
    }
}
