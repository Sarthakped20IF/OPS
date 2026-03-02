package com.example.Product_Service.config;

import com.example.Product_Service.batch.processor.ProductProcess;
import com.example.Product_Service.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class ProductCSVBatchConfig {
    private final ProductProcess productProcess;

    @Bean
    public FlatFileItemReader<ProductEntity> csvReader(){
        FlatFileItemReader<ProductEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("product.csv"));
        reader.setLinesToSkip(1);


        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("prodName,price,stock,category");

        BeanWrapperFieldSetMapper<ProductEntity> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(ProductEntity.class);

        DefaultLineMapper<ProductEntity> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);
        reader.setLineMapper(lineMapper);
        return reader;

    }

    @Bean
    public ItemProcessor<ProductEntity,ProductEntity> processor(){
        return product -> {
            product.setPrice(product.getPrice() * 0.5);
            return product;
        };
    }

    @Bean
    public JpaItemWriter<ProductEntity> writer(EntityManagerFactory emf){
        JpaItemWriter<ProductEntity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;

    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                     FlatFileItemReader<ProductEntity>  reader,JpaItemWriter<ProductEntity> writer){
        return new StepBuilder("Product-step",jobRepository)
                .<ProductEntity,ProductEntity>chunk(5,transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step){
        return new JobBuilder("Product-job",jobRepository)
                .start(step)
                .build();
    }
}
