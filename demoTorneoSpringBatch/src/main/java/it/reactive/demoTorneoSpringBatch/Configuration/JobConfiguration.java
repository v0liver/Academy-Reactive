package it.reactive.demoTorneoSpringBatch.Configuration;


import it.reactive.demoTorneoSpringBatch.DTO.GiocatoreSquadraDTO;
import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import it.reactive.demoTorneoSpringBatch.model.TipoFile;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class JobConfiguration {


    @Bean(Costanti.Job)
    public Job Job(JobRepository jobRepository,
                   @Qualifier(Costanti.StepDelete) Step stepDelete,
                   @Qualifier(Costanti.StepInsert) Step stepInsert,
                   @Qualifier(Costanti.StepCSV) Step stepCSV) {

        return new JobBuilder(Costanti.Job, jobRepository)
                .start(stepDelete)
                .next(stepInsert)
                .next(stepCSV)
                .build();


    }

    @Bean(Costanti.StepDelete)
    public Step StepDelete(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           @Qualifier(Costanti.taskletDelete) Tasklet tasklet) {
        return new StepBuilder(Costanti.StepDelete, jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();

    }

    @Bean(Costanti.StepInsert)
    public Step stepInsert(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                           @Qualifier(Costanti.FLAT_ITEM_STREAM_READER_PosizionaleCP) ItemStreamReader<List<String>> reader,
                           @Qualifier(Costanti.PROCESSOR_INSERT) ClassifierCompositeItemProcessor<List<String>, TipoFile> processor,
                           @Qualifier(Costanti.WRITER_INSERT) ClassifierCompositeItemWriter<TipoFile> writer) {
        return new StepBuilder(Costanti.StepInsert, jobRepository)
                .<List<String>, TipoFile>chunk(Costanti.ChunkSize, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean(Costanti.StepCSV)
    public Step stepCSV(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        @Qualifier(Costanti.Reader_Csv) ItemStreamReader<GiocatoreSquadraDTO> reader,
                        @Qualifier(Costanti.WRITER_CSV) ItemStreamWriter<GiocatoreSquadraDTO> writer) {
        return new StepBuilder(Costanti.StepCSV, jobRepository)
                .<GiocatoreSquadraDTO, GiocatoreSquadraDTO>chunk(Costanti.ChunkSize, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }


}


