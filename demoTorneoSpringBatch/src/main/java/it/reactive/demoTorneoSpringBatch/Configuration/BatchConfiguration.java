package it.reactive.demoTorneoSpringBatch.Configuration;

import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {


    @Bean(Costanti.primoJob)
    public Job Job(JobRepository jobRepository,
                   @Qualifier(Costanti.primoStep) Step primoStep){

        return new JobBuilder(Costanti.primoJob,jobRepository)
                .start(primoStep)
                .build();


    }


    @Bean(Costanti.primoStep)
    public Step primoStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder(Costanti.primoStep,jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        return RepeatStatus.FINISHED;
                    }
                },transactionManager)
                .build();

    }
}
