package it.reactive.demoTorneoSpringBatch.writer;

import it.reactive.demoTorneoSpringBatch.DTO.*;
import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import it.reactive.demoTorneoSpringBatch.model.TipoFile;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
public class Writer {
    @Bean(Costanti.WRITER_INSERT)
    public ClassifierCompositeItemWriter<TipoFile> writerInsert(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource,
                                                                @Qualifier(Costanti.WRITER_SQUADRA_TIFOSERIA) CustomWriterSquadraTifoseria writerSquadraTifoseria) {
        Classifier<TipoFile, ItemWriter<? super TipoFile>> classifier = tipoFile -> {
            if (tipoFile instanceof TorneoDTO) {
                return new JdbcBatchItemWriterBuilder<>()
                        .dataSource(dataSource)
                        .itemPreparedStatementSetter((file, ps) -> {
                            TorneoDTO torneoDTO = (TorneoDTO) file;
                            ps.setString(1, torneoDTO.getNomeTorneo());
                            ps.setInt(2, torneoDTO.getId());
                        })
                        .sql("insert into torneo(nome_torneo,id) values (?,?)")
                        .build();

            } else if (tipoFile instanceof SquadraDTO) {
                return writerSquadraTifoseria;
//                return new CompositeItemWriterBuilder<>()
//                        .delegates(
//                                new JdbcBatchItemWriterBuilder<>()
//                                        .dataSource(dataSource)
//                                        .sql("insert into squadra(nome, colori_sociali) values (?,?)")
//                                        .itemPreparedStatementSetter((file, ps) -> {
//                                            SquadraDTO squadraDTO = (SquadraDTO) file;
//                                            ps.setString(1, squadraDTO.getNome());
//                                            ps.setString(2, squadraDTO.getColoriSociali());
//                                        })
//                                        .build(),
//                                new JdbcBatchItemWriterBuilder<>()
//                                        .dataSource(dataSource)
//                                        .sql("insert into tifoseria (nome_tifoseria, id_squadra) values (?,?)")
//                                        .itemPreparedStatementSetter((file, ps) -> {
//                                            SquadraDTO squadraDTO = (SquadraDTO) file;
//                                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
//                                            ExecutionContext stepContext = stepExecution.getExecutionContext();
//                                            ps.setString(1, squadraDTO.getTifoseria());
//                                            ps.setInt(2, (Integer) stepContext.get(squadraDTO.getNome() + "squadra"));
//                                        })
//                                        .build()
//                        ).build();

            } else if (tipoFile instanceof GiocatoreDto) {
                return new JdbcBatchItemWriterBuilder<>()
                        .dataSource(dataSource)
                        .sql("insert into giocatore(nome_cognome, id_squadra) values (?,?)")
                        .itemPreparedStatementSetter((file, ps) -> {
                            GiocatoreDto giocatoreDto = (GiocatoreDto) file;
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            ps.setString(1, giocatoreDto.getNomeCognome());
                            ps.setInt(2, (Integer) stepContext.get(giocatoreDto.getNomeSquadra() + "squadra"));
                        })
                        .build();

            } else if (tipoFile instanceof SquadraTorneoDTO) {
                return new JdbcBatchItemWriterBuilder<>()
                        .dataSource(dataSource)
                        .sql("insert into squadra_torneo(id_squadra, id_torneo) values (?,?)")
                        .itemPreparedStatementSetter((file, ps) -> {
                            SquadraTorneoDTO squadraTorneoDTO = (SquadraTorneoDTO) file;
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            ps.setInt(1, (Integer) stepContext.get(squadraTorneoDTO.getNomeSquadra() + "squadra"));
                            ps.setInt(2, (Integer) stepContext.get(squadraTorneoDTO.getNomeTorneo() + "torneo"));
                        })
                        .build();

            }
            throw new RuntimeException("TipoRecord non gestito: " + tipoFile.getClass().getSimpleName());
        };

        ClassifierCompositeItemWriter<TipoFile> classifierWriter = new ClassifierCompositeItemWriter<>();
        classifierWriter.setClassifier(classifier);
        return classifierWriter;
    }

    // per avere una scrittura formattata personalizzata sul csv
    @Bean(Costanti.WRITER_CSV)
    public FlatFileItemWriter<GiocatoreSquadraDTO> writerCSV() {
        return new FlatFileItemWriterBuilder<GiocatoreSquadraDTO>()
                .name(Costanti.WRITER_CSV)
                .resource(new FileSystemResource("file/batchTorneoGiocatore.csv"))
                .lineAggregator(new LineAggregator<GiocatoreSquadraDTO>() {
                    @Override
                    public String aggregate(GiocatoreSquadraDTO item) {
                        return String.format(
                                "%-15d;%-15s;%-15d;%-15s;%-15s;%-15s",//per settare come vuoi la stringa
                                item.getIdGiocatore(),
                                item.getNomeCognome(),
                                item.getNumeroAmmonizioni(),
                                item.getNomeSquadra(),
                                item.getNomeTifoseria() != null ? item.getNomeTifoseria() : "",
                                item.getColoriSociali()
                        );
                    }
                })
                .headerCallback(writer -> writer.write(
                        String.format("%-15s;%-15s;%-15s;%-15s;%-15s;%-15s",
                                "Id Giocatore",
                                "Nome Cognome",
                                "Ammonizioni",
                                "Squadra",
                                "Tifoseria",
                                "Colori Sociali")))//per settare una riga fissa all inizio
                .build();
    }
//per scrivere un file delimitato da un certo carattere per creare file csv
//    @Bean(Costanti.WRITER_CSV)
//    public FlatFileItemWriter<GiocatoreSquadraDTO> writerCSV() {
//        return new FlatFileItemWriterBuilder<GiocatoreSquadraDTO>()
//                .name(Costanti.WRITER_CSV)
//                .resource(new FileSystemResource("file/batchTorneoGiocatore.csv"))
//                .lineAggregator(new DelimitedLineAggregator<GiocatoreSquadraDTO>() {{
//                    setDelimiter(";");
//                    setFieldExtractor(new BeanWrapperFieldExtractor<GiocatoreSquadraDTO>() {{
//                        setNames(new String[]{"idGiocatore", "nomeCognome", "numeroAmmonizioni", "nomeSquadra", "nomeTifoseria", "coloriSociali"});//nome attributi dell oggetto e non del db
//                    }});
//                }})
//                .build();
//    }


//    @Bean(Costanti.WRITER_SQUADRA)
//    public ItemStreamWriter<TipoFile> writerSquadra(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
//        return new ItemStreamWriter<TipoFile>() {
//            @Override
//            public void write(Chunk<? extends TipoFile> chunk) throws Exception {
//                chunk.forEach(tipoFile -> {
//                    try {
//                        SquadraDTO squadraDTO = (SquadraDTO) tipoFile;
//                        Connection con = dataSource.getConnection();
//                        PreparedStatement ps = con.prepareStatement("insert into squadra(nome, colori_sociali) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
//                        ps.setString(1, squadraDTO.getNome());
//                        ps.setString(2, squadraDTO.getColoriSociali());
//                        ps.executeUpdate();
//                        ResultSet rs = ps.getGeneratedKeys();
//                        int id = 0;
//                        if (rs.next()) {
//                            id = rs.getInt(1);
//                            squadraDTO.setId(id);
//                        }
//                        if (!squadraDTO.getTifoseria().equals("-")) {
//                            ps = con.prepareStatement("insert into tifoseria (nome_tifoseria, id_squadra) values (?,?)");
//                            ps.setString(1, squadraDTO.getTifoseria());
//                            ps.setInt(2, id);
//                            ps.executeUpdate();
//                        }
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                });
//            }
//        };
//    }
//
//    @Bean(Costanti.WRITER_Giocatore)
//    public ItemStreamWriter<TipoFile> writerGiocatore(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
//        return new ItemStreamWriter<TipoFile>() {
//            @Override
//            public void write(Chunk<? extends TipoFile> chunk) throws Exception {
//                Connection con = dataSource.getConnection();
//                PreparedStatement ps2 = con.prepareStatement("select * from squadra where nome=?");
//                chunk.forEach(tipoFile -> {
//
//                    try {
//                        GiocatoreDto giocatoreDto = (GiocatoreDto) tipoFile;
//
//
//                        ps2.setString(1, giocatoreDto.getNomeSquadra());
//                        ResultSet rs = ps2.executeQuery();
//                        int idSquadra = 0;
//                        if (rs.next()) {
//
//                            idSquadra = rs.getInt("id");
//                        }
//                        PreparedStatement ps = con.prepareStatement("insert into giocatore(nome_cognome,id_squadra) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
//                        ps.setString(1, giocatoreDto.getNomeCognome());
//                        ps.setInt(2, idSquadra);
//                        ps.executeUpdate();
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//
//
//                });
//            }
//        };
//    }
//
//    @Bean(Costanti.WRITER_Squadra_Torneo)
//    public ItemStreamWriter<TipoFile> writerSquadraTorneo(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
//        return new ItemStreamWriter<TipoFile>() {
//            @Override
//            public void write(Chunk<? extends TipoFile> chunk) throws Exception {
//                chunk.forEach(tipoFile -> {
//                    try {
//                        SquadraTorneoDTO squadraTorneoDTO = (SquadraTorneoDTO) tipoFile;
//                        Connection con = dataSource.getConnection();
//                        PreparedStatement ps2 = con.prepareStatement("select * from squadra where nome=?");
//                        ps2.setString(1, squadraTorneoDTO.getNomeSquadra());
//                        ResultSet rs = ps2.executeQuery();
//                        int idSquadra = 0;
//                        if (rs.next()) {
//                            idSquadra = rs.getInt("id");
//                        }
//                        PreparedStatement ps3 = con.prepareStatement("select * from torneo where nome_torneo=?");
//                        ps3.setString(1, squadraTorneoDTO.getNomeTorneo());
//                        ResultSet rs2 = ps3.executeQuery();
//                        int idTorneo = 0;
//                        if (rs2.next()) {
//                            idTorneo = rs.getInt("id");
//                        }
//                        PreparedStatement ps = con.prepareStatement("insert into squadra_torneo(id_squadra,id_torneo) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
//                        ps.setInt(1, idSquadra);
//                        ps.setInt(2, idTorneo);
//                        ps.executeUpdate();
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                });
//            }
//        };
//    }
}
