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
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.BindException;

import javax.sql.DataSource;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BatchConfiguration {


    @Bean(Costanti.Job)
    public Job Job(JobRepository jobRepository,
                   @Qualifier(Costanti.StepDelete) Step primoStep){

        return new JobBuilder(Costanti.Job,jobRepository)
                .start(primoStep)
                .build();


    }




    @Bean(Costanti.StepDelete)
    public Step StepDelete(JobRepository jobRepository,@Qualifier("torneoTransactionManager") PlatformTransactionManager transactionManager,@Qualifier(Costanti.taskletDelete)Tasklet tasklet){
        return new StepBuilder(Costanti.StepDelete,jobRepository)
                .tasklet(tasklet,transactionManager)
                .build();

    }

    @Bean(Costanti.StepInsert)
    public Step stepInsert(JobRepository jobRepository,@Qualifier(Costanti.transactionalManagerTorneo) PlatformTransactionManager transactionManager,
                           @Qualifier(Costanti.FLAT_ITEM_STREAM_READER_PosizionaleCP)ItemStreamReader<List<String>> reader,
                           @Qualifier(Costanti.FLAT_ITEM_STREAM_WRITER_PosizionaleCP) ItemWriter<Object> writer){
        return new StepBuilder(Costanti.StepDelete,jobRepository)
                .chunk(Costanti.ChunkSize,transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean(Costanti.FLAT_ITEM_STREAM_READER_PosizionaleCP)
    public FlatFileItemReader<List<String>> insertReader(){
        FixedLengthTokenizer tokenizer=new FixedLengthTokenizer();
        tokenizer.setColumns(new Range(1,2),new Range(3,102), new Range(103,202),new Range(203));
        FieldSetMapper<List<String>> fsm = new FieldSetMapper<List<String>>() {
            @Override
            public List<String> mapFieldSet(FieldSet fieldSet) throws BindException {
                List<String> valoriRecord = new ArrayList<>();
                valoriRecord.add(fieldSet.readString(0));
                valoriRecord.add(fieldSet.readString(1));
                valoriRecord.add(fieldSet.readString(2));
                valoriRecord.add(fieldSet.readString(3));
                return valoriRecord;
            }
        };

        return new FlatFileItemReaderBuilder<List<String>>()
                .name(Costanti.FLAT_ITEM_STREAM_READER_PosizionaleCP)
                .lineTokenizer(tokenizer)
                .resource(new FileSystemResource("file/batchTorneo.txt"))
                .fieldSetMapper(fsm)
                .build();
    }


    @Bean(Costanti.FLAT_ITEM_STREAM_WRITER_PosizionaleCP)
    public ItemWriter<Object> itemWriter() {
        return new ItemWriter<Object>() {
            @Override
            public void write(Chunk<? extends Object> chunk) throws Exception {
                for (Object item : chunk.getItems()) {
                    // Se l'oggetto è una lista di stringhe, lo puoi castare prima di usarlo
                    if (item instanceof List) {
                        List<String> list = (List<String>) item;
                        System.out.println(list);
                    }
                }
            }
        };
    }




    @Bean(Costanti.taskletDelete)
    public Tasklet tasklet(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource){
       return new Tasklet() {
           @Override
           public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
               System.out.println("Primo Step");
               try {
                   PreparedStatement ps = dataSource.getConnection().prepareStatement("delete from squadra cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from giocatore cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from tifoseria cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from squadra_torneo cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from torneo cascade");
                   ps.executeUpdate();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               //GiocatoreModel giocatoreModel = giocatoreJpaRepository.findById(1).orElseThrow(()->new GiocatoreNonPresenteException());
//               giocatoreJpaRepository.delete(giocatoreModel);
               return RepeatStatus.FINISHED;
           }
       };
    }
}
