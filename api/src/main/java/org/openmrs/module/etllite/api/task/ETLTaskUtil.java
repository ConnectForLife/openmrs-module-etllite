package org.openmrs.module.etllite.api.task;

import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;

public final class ETLTaskUtil {

    public static Date nextDate(String cron) {
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
        return cronSequenceGenerator.next(new Date());
    }

    public static String generateTaskName(String subject, String jobId) {
        return String.format("%s-%s", subject, jobId);
    }

    public static String removeYearsFromCronIfNeeded(String cron) {
        String[] fields = StringUtils.tokenizeToStringArray(cron, " ");
        String parsedCron;
        if (fields.length > 6) {
            parsedCron = StringUtils.arrayToDelimitedString(Arrays.copyOf(fields, fields.length - 1), " ");
        } else {
            parsedCron = cron;
        }

        return parsedCron;
    }

    private ETLTaskUtil() {
    }
}
