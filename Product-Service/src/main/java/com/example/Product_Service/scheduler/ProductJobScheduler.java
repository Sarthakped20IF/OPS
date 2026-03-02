package com.example.Product_Service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job productJob;

    @Scheduled(cron = "0 0 9 * * ?")
    public void runProductJob() {
        try {

            log.info("Batch job started at: {}", LocalDateTime.now());

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) // unique parameter
                    .toJobParameters();

            jobLauncher.run(productJob, jobParameters);

            log.info("Batch job finished at: {}", LocalDateTime.now());

        } catch (Exception e) {
            log.error("Batch job failed: {}", e.getMessage());
        }
    }

    @Scheduled(fixedRate = 5000)
    public void testScheduler() {
        System.out.println("Scheduler working: " + LocalDateTime.now());
    }
}
