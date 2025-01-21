package it.reactive.demoTorneoSpringBatch.Configuration;


import it.reactive.demoTorneoSpringBatch.DTO.*;
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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.*;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.BindException;

import javax.sql.DataSource;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BatchConfiguration {


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


    @Bean(Costanti.taskletDelete)
    public Tasklet tasklet(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Primo Step");
                Connection con = dataSource.getConnection();
                try {
                    PreparedStatement ps = dataSource.getConnection().prepareStatement("truncate table squadra cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table giocatore cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table tifoseria cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table squadra_torneo cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table torneo cascade");
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

    @Bean(Costanti.FLAT_ITEM_STREAM_READER_PosizionaleCP)
    public FlatFileItemReader<List<String>> insertReader() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range(1, 2), new Range(3, 102), new Range(103, 202), new Range(203));
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
                            giocatoreDto.setNomeSquadra(lista.get(2));
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
    public ClassifierCompositeItemWriter<TipoFile> writerInsert(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource,
                                                                @Qualifier(Costanti.WRITER_SQUADRA) ItemStreamWriter<TipoFile> writerSquadra,
                                                                @Qualifier(Costanti.WRITER_Giocatore) ItemStreamWriter<TipoFile> writerGiocatore,
                                                                @Qualifier(Costanti.WRITER_Squadra_Torneo) ItemStreamWriter<TipoFile> writerSquadraTorneo) {
        Classifier<TipoFile, ItemWriter<? super TipoFile>> classifier = tipoFile -> {
            if (tipoFile instanceof TorneoDTO) {
                return new JdbcBatchItemWriterBuilder<>()
                        .dataSource(dataSource)
                        .itemPreparedStatementSetter((file, ps) -> {
                            TorneoDTO torneoDTO = (TorneoDTO) file;
                            ps.setString(1, torneoDTO.getNomeTorneo());
                        })
                        .sql("insert into torneo(nome_torneo) values (?)")
                        .build();
            } else if (tipoFile instanceof SquadraDTO) {
                return writerSquadra;
            } else if (tipoFile instanceof GiocatoreDto) {
                return writerGiocatore;
            } else if (tipoFile instanceof SquadraTorneoDTO) {
                return writerSquadraTorneo;
            }

            throw new RuntimeException("TipoRecord non gestito: " + tipoFile.getClass().getSimpleName());
        };
        ClassifierCompositeItemWriter<TipoFile> classifierWriter = new ClassifierCompositeItemWriter<>();
        classifierWriter.setClassifier(classifier);
        return classifierWriter;
    }

    @Bean(Costanti.WRITER_SQUADRA)
    public ItemStreamWriter<TipoFile> writerSquadra(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        return new ItemStreamWriter<TipoFile>() {
            @Override
            public void write(Chunk<? extends TipoFile> chunk) throws Exception {
                chunk.forEach(tipoFile -> {
                    try {
                        SquadraDTO squadraDTO = (SquadraDTO) tipoFile;
                        Connection con = dataSource.getConnection();
                        PreparedStatement ps = con.prepareStatement("insert into squadra(nome, colori_sociali) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        ps.setString(1, squadraDTO.getNome());
                        ps.setString(2, squadraDTO.getColoriSociali());
                        ps.executeUpdate();
                        ResultSet rs = ps.getGeneratedKeys();
                        int id = 0;
                        if (rs.next()) {
                            id = rs.getInt(1);
                            squadraDTO.setId(id);
                        }
                        if (!squadraDTO.getTifoseria().equals("-")) {
                            ps = con.prepareStatement("insert into tifoseria (nome_tifoseria, id_squadra) values (?,?)");
                            ps.setString(1, squadraDTO.getTifoseria());
                            ps.setInt(2, id);
                            ps.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        };
    }

    @Bean(Costanti.WRITER_Giocatore)
    public ItemStreamWriter<TipoFile> writerGiocatore(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        return new ItemStreamWriter<TipoFile>() {
            @Override
            public void write(Chunk<? extends TipoFile> chunk) throws Exception {
                Connection con = dataSource.getConnection();
                chunk.forEach(tipoFile -> {

                        try {
                            GiocatoreDto giocatoreDto = (GiocatoreDto) tipoFile;

                            PreparedStatement ps2 = con.prepareStatement("select * from squadra where nome=?");
                            ps2.setString(1, giocatoreDto.getNomeSquadra());
                            ResultSet rs = ps2.executeQuery();
                            int idSquadra = 0;
                            if (rs.next()) {

                                idSquadra = rs.getInt("id");
                            }
                            PreparedStatement ps = con.prepareStatement("insert into giocatore(nome_cognome,id_squadra) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                            ps.setString(1, giocatoreDto.getNomeCognome());
                            ps.setInt(2, idSquadra);
                            ps.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }


                    });
            }
        };
    }

    @Bean(Costanti.WRITER_Squadra_Torneo)
    public ItemStreamWriter<TipoFile> writerSquadraTorneo(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        return new ItemStreamWriter<TipoFile>() {
            @Override
            public void write(Chunk<? extends TipoFile> chunk) throws Exception {
                chunk.forEach(tipoFile -> {
                    try {
                        SquadraTorneoDTO squadraTorneoDTO = (SquadraTorneoDTO) tipoFile;
                        Connection con = dataSource.getConnection();
                        PreparedStatement ps2 = con.prepareStatement("select * from squadra where nome=?");
                        ps2.setString(1, squadraTorneoDTO.getNomeSquadra());
                        ResultSet rs = ps2.executeQuery();
                        int idSquadra = 0;
                        if (rs.next()) {
                            idSquadra = rs.getInt("id");
                        }
                        PreparedStatement ps3 = con.prepareStatement("select * from torneo where nome_torneo=?");
                        ps3.setString(1, squadraTorneoDTO.getNomeTorneo());
                        ResultSet rs2 = ps3.executeQuery();
                        int idTorneo = 0;
                        if (rs2.next()) {
                            idTorneo = rs.getInt("id");
                        }
                        PreparedStatement ps = con.prepareStatement("insert into squadra_torneo(id_squadra,id_torneo) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, idSquadra);
                        ps.setInt(2, idTorneo);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        };
    }

    @Bean(Costanti.Reader_Csv)
    public JdbcCursorItemReader<GiocatoreSquadraDTO> giocatoreReader(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<GiocatoreSquadraDTO>()
                .name(Costanti.Reader_Csv)
                .dataSource(dataSource)
                .sql("select g.*, s.nome , s.colori_sociali, t.nome_tifoseria from giocatore g squadra s on g.id_squadra = s.id left join tifoseria t on t.id_squadra = s.id")
                .rowMapper((rs, rowNum) -> {
                    GiocatoreSquadraDTO giocatore = new GiocatoreSquadraDTO();
                    giocatore.setNomeCognome(rs.getString("nome_cognome"));
                    giocatore.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                    giocatore.setColoriSociali(rs.getString("colori_sociali"));
                    giocatore.setNomeSquadra(rs.getString("nome"));
                    giocatore.setNomeTifoseria(rs.getString("nome_tifoseria"));
                    giocatore.setIdGiocatore(rs.getInt("id"));
                    return giocatore;
                })

                .build();
    }

    @Bean(Costanti.WRITER_CSV)
    public FlatFileItemWriter<GiocatoreSquadraDTO> writerCSV() {
        return new FlatFileItemWriterBuilder<GiocatoreSquadraDTO>()
                .name(Costanti.WRITER_CSV)
                .resource(new FileSystemResource("file/batchTorneoGiocatore.csv"))
                .lineAggregator(new DelimitedLineAggregator<GiocatoreSquadraDTO>() {{
                    setDelimiter(";");
                    setFieldExtractor(new BeanWrapperFieldExtractor<GiocatoreSquadraDTO>() {{
                        setNames(new String[]{"idGiocatore", "nomeCognome", "nomeSquadra", "nomeTifoseria", "coloriSociali", "numeroAmmonizioni"});
                    }});
                }})
                .build();
    }
}


