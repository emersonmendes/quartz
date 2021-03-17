package br.com.emersonmendes.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;


@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class LogJob extends QuartzJobBean {

    @Override
    public void executeInternal(JobExecutionContext ctx) {

        try {

            System.out.println("Running job: " + ctx.getJobDetail().getKey());
            System.out.println("Thread id: " + Thread.currentThread().getId());
            System.out.println("Thread name: " + Thread.currentThread().getName());
            System.out.println("-----------------------------------------------------------------------------------------------\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}