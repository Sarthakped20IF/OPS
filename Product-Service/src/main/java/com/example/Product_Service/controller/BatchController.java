package com.example.Product_Service.controller;

import com.example.Product_Service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/batch")
//@RequiredArgsConstructor
//public class BatchController {
//    private JobLauncher jobLauncher;
//    private Job job;
//
//
//    @PostMapping("/run")
//    public String runBatch() throws Exception {
//
//        jobLauncher.run(job,
//                new JobParametersBuilder()
//                        .addLong("time", System.currentTimeMillis())
//                        .toJobParameters());
//
//        return "Batch Started";
//    }
//}
@RestController
@RequestMapping("/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job productJob;

    public BatchController(JobLauncher jobLauncher, Job productJob) {
        this.jobLauncher = jobLauncher;
        this.productJob = productJob;
    }

    @PostMapping("/run")
    public String runBatch() throws Exception {

        jobLauncher.run(productJob,
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters());

        return "Batch Job Started";
    }
}
