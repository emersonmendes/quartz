package br.com.emersonmendes.quartz.jobs;

import org.quartz.spi.InstanceIdGenerator;

import java.util.UUID;

public class QuartzInstanceIdGenerator implements InstanceIdGenerator {
    @Override
    public String generateInstanceId() {
        return "job-" + UUID.randomUUID().toString();
    }
}