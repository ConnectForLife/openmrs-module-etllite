package org.openmrs.module.etllite.api.task;

import org.openmrs.api.context.Context;
import org.openmrs.module.etllite.api.constants.Constants;
import org.openmrs.module.etllite.api.exception.ETLRuntimeException;
import org.openmrs.scheduler.SchedulerException;
import org.openmrs.scheduler.tasks.AbstractTask;

public abstract class AbstractCronTask extends AbstractTask {

    @Override
    public void execute() {
        try {
            executeTask();
        } finally {
            rescheduleTask();
        }
    }

    protected abstract void executeTask();

    private void rescheduleTask() {
        try {
            getTaskDefinition().setStartTime(ETLTaskUtil.nextDate(getTaskDefinition().getProperty(Constants.CRON_PROPERTY)));
            Context.getSchedulerService().scheduleTask(getTaskDefinition());
        } catch (SchedulerException ex) {
            throw new ETLRuntimeException(ex);
        }
    }
}
