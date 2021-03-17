package br.com.emersonmendes.quartz.service;

import br.com.emersonmendes.quartz.jobs.LogJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AppService {

    public void schedule(String name) {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try {

            Scheduler scheduler = schedulerFactory.getScheduler();

            final JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.putAsString("something", 23);

            name = name + UUID.randomUUID().toString();

            final String jobIdentity = "job-" + name;
            final String triggerIdentity = "trigger-" + name;

            JobDetail job = JobBuilder.newJob(LogJob.class)
                .withIdentity(jobIdentity)
                .withDescription("description " + jobIdentity)
                .setJobData(jobDataMap)
                .storeDurably()
                .requestRecovery()
                .build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .withIdentity(triggerIdentity)
                .withDescription("description " + triggerIdentity)
                .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public List<String> getJobs() {

        List<String> result = new ArrayList<>();

        try {

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();

            for (String groupName : scheduler.getJobGroupNames()) {

                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();

                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    Date nextFireTime = triggers.get(0).getNextFireTime();

                    result.add("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime);

                }

            }

            return result;

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return null;

    }

}
