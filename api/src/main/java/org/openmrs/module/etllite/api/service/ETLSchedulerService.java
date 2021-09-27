package org.openmrs.module.etllite.api.service;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.etllite.api.event.ETLEvent;
import org.openmrs.module.etllite.api.task.AbstractCronTask;

public interface ETLSchedulerService extends OpenmrsService {

    void safeScheduleJob(ETLEvent event, String cronExp, AbstractCronTask cronTask);

    void safeUnscheduleJob(String subject, String jobId);
}
