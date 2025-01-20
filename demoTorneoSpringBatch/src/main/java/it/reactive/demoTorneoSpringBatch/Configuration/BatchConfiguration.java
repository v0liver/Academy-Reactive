package it.reactive.demoTorneoSpringBatch.Configuration;


import it.reactive.demoTorneoSpringBatch.DTO.GiocatoreDto;
import it.reactive.demoTorneoSpringBatch.DTO.SquadraDTO;
import it.reactive.demoTorneoSpringBatch.DTO.SquadraTorneoDTO;
import it.reactive.demoTorneoSpringBatch.DTO.TorneoDTO;
import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import it.reactive.demoTorneoSpringBatch.model.TipoFile;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
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
                   @Qualifier(Costanti.StepDelete) Step stepDelete,
                   @Qualifier(Costanti.StepInsert) Step stepInsert){

        return new JobBuilder(Costanti.Job,jobRepository)
                .start(stepDelete)
                .next(stepInsert)
                .build();


    }




    @Bean(Costanti.StepDelete)
    public Step StepDelete(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           @Qualifier(Costanti.taskletDelete)Tasklet tasklet){
        return new StepBuilder(Costanti.StepDelete,jobRepository)
                .tasklet(tasklet,transactionManager)
                .build();

    }

    @Bean(Costanti.StepInsert)
    public Step stepInsert(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                           @Qualifier(Costanti.FLAT_ITEM_STREAM_READER_PosizionaleCP)ItemStreamReader<List<String>> reader,
                           @Qualifier(Costanti.PROCESSOR_INSERT) ClassifierCompositeItemProcessor<List<String>, TipoFile> processor,
                           @Qualifier(Costanti.WRITER_INSERT) ClassifierCompositeItemWriter<TipoFile> writer ){
        return new StepBuilder(Costanti.StepInsert, jobRepository)
                .<List<String>, TipoFile>chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
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

    @Bean(Costanti.PROCESSOR_INSERT)
    public ClassifierCompositeItemProcessor<List<String>, TipoFile> processorInsert() {
        Classifier<List<String>, ItemProcessor<?, ? extends TipoFile>> classifier = new Classifier<>() {
            @Override
            public ItemProcessor classify(List<String> lista) {
                if (lista.get(0).equals("TO")) {
                    return new ItemProcessor<List<String>, TipoFile>() {
                        @Override
                        public TipoFile process(List<String> item) throws Exception {
                            TorneoDTO torneoDTO = new TorneoDTO();
                            torneoDTO.setNomeTorneo(lista.get(1));
                            return torneoDTO;
                        }
                    };
                } else if (lista.get(0).equals("SQ")) {
                    return new ItemProcessor<List<String>, TipoFile>() {
                        @Override
                        public TipoFile process(List<String> item) throws Exception {
                            SquadraDTO squadraDTO = new SquadraDTO();
                            squadraDTO.setNome(lista.get(1));
                            squadraDTO.setColoriSociali(lista.get(2));
                            squadraDTO.setTifoseria(lista.get(3));
                            return squadraDTO;
                        }
                    };
                } else if (lista.get(0).equals("GI")) {
                    return new ItemProcessor<List<String>, TipoFile>() {
                        @Override
                        public TipoFile process(List<String> item) throws Exception {
                            GiocatoreDto giocatoreDto = new GiocatoreDto();
                            String nomeCognome = lista.get(1).substring(0, 49).trim() + " " + lista.get(1).substring(50).trim();
                            giocatoreDto.setNomeCognome(nomeCognome);
                            return giocatoreDto;
                        }
                    };
                } else if (lista.get(0).equals("TS")) {
                    return new ItemProcessor<List<String>, TipoFile>() {
                        @Override
                        public TipoFile process(List<String> item) throws Exception {
                            SquadraTorneoDTO squadraTorneoDTO = new SquadraTorneoDTO();
                            squadraTorneoDTO.setNomeTorneo(lista.get(1));
                            squadraTorneoDTO.setNomeSquadra(lista.get(2));
                            return squadraTorneoDTO;
                        }
                    };
                } else {
                    return new ItemProcessor<List<String>, TipoFile>() {
                        @Override
                        public TipoFile process(List<String> item) throws Exception {
                            return new TipoFile();
                        }
                    };
                }
            }
        };
        ClassifierCompositeItemProcessor<List<String>, TipoFile> classifierProcessor = new ClassifierCompositeItemProcessor<>();
        classifierProcessor.setClassifier(classifier);
        return classifierProcessor;
    }

    @Bean(Costanti.WRITER_INSERT)
    public ClassifierCompositeItemWriter<TipoFile> writerInsert(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource){
        Classifier<TipoFile, ItemWriter<? super TipoFile>> classifier = tipoRecord -> {
            if (tipoRecord instanceof TorneoDTO){
                return new JdbcBatchItemWriterBuilder<>().dataSource(dataSource)
                        .itemPreparedStatementSetter((item, ps) -> {
                            TorneoDTO torneoDTO = (TorneoDTO) item;
                            ps.setString(1, torneoDTO.getNomeTorneo());
                        }).sql("insert into torneo(nome_torneo) values (?)")
                        .build();
            }
            throw new RuntimeException("TipoRecord non gestito: " + tipoRecord.getClass().getSimpleName());
        };
        ClassifierCompositeItemWriter<TipoFile> classifierWriter = new ClassifierCompositeItemWriter<>();
        classifierWriter.setClassifier(classifier);
        return classifierWriter;
    }

//    @Bean
//    public ClassifierCompositeItemProcessor<List<String>,TipoFile> classifierCompositeItemProcessor(){
//        Classifier<List<String>,ItemProcessor<?,? extends TipoFile>> classifier = new Classifier<List<String>, ItemProcessor<?, ? extends TipoFile>>() {
//            @Override
//            public ItemProcessor<?, ? extends TipoFile> classify(List<String> listaAttributi) {
//                String tipo = listaAttributi.get(0);
//                if (tipo.equals("TO")){
//                    return new ItemProcessor<List<String>, TipoFile>() {
//                        @Override
//                        public TipoFile process(List<String> item) throws Exception {
//
//                            return null;
//                        }
//                    }
//                }
//            }
//        }
//    }




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
