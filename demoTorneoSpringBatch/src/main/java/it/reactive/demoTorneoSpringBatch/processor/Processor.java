package it.reactive.demoTorneoSpringBatch.processor;

import it.reactive.demoTorneoSpringBatch.DTO.GiocatoreDto;
import it.reactive.demoTorneoSpringBatch.DTO.SquadraDTO;
import it.reactive.demoTorneoSpringBatch.DTO.SquadraTorneoDTO;
import it.reactive.demoTorneoSpringBatch.DTO.TorneoDTO;
import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import it.reactive.demoTorneoSpringBatch.model.TipoFile;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Configuration
public class Processor {

    @Bean(Costanti.PROCESSOR_INSERT)
    public ClassifierCompositeItemProcessor<List<String>, TipoFile> processorInsert(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        Connection con;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Classifier<List<String>, ItemProcessor<?, ? extends TipoFile>> classifier = new Classifier<>() {
            @Override
            public ItemProcessor classify(List<String> lista) {
                if (lista.get(0).equals("TO")) {
                    return new ItemProcessor<List<String>, TipoFile>() {
                        @Override
                        public TipoFile process(List<String> item) throws Exception {
                            PreparedStatement ps = con.prepareStatement("SELECT nextval(pg_get_serial_sequence('torneo', 'id'))");
                            ResultSet rs = ps.executeQuery();
                            rs.next();
                            Integer idTorneo = rs.getInt(1);
                            TorneoDTO torneoDTO = new TorneoDTO();
                            torneoDTO.setNomeTorneo(lista.get(1));
                            torneoDTO.setId(idTorneo);
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            stepContext.put(torneoDTO.getNomeTorneo() + "torneo", torneoDTO.getId());
                            return torneoDTO;
                        }
                    };
                } else if (lista.get(0).equals("SQ")) {
                    return new ItemProcessor<List<String>, TipoFile>() {
                        @Override
                        public TipoFile process(List<String> item) throws Exception {
                            PreparedStatement ps = con.prepareStatement("SELECT nextval(pg_get_serial_sequence('squadra', 'id'))"); //restituisce l utlimo id inserito sul db incrementato di 1 e lo incrementa anche sul db
                            ResultSet rs = ps.executeQuery();
                            rs.next();
                            Integer idSquadra = rs.getInt(1);
                            SquadraDTO squadraDTO = new SquadraDTO();
                            squadraDTO.setId(idSquadra);
                            squadraDTO.setNome(lista.get(1));
                            squadraDTO.setColoriSociali(lista.get(2));
                            squadraDTO.setTifoseria(lista.get(3));
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            stepContext.put(squadraDTO.getNome() + "squadra", squadraDTO.getId());
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
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            squadraTorneoDTO.setIdSquadra((Integer) stepContext.get(squadraTorneoDTO.getNomeSquadra() + "squadra"));
                            squadraTorneoDTO.setIdTorneo((Integer) stepContext.get(squadraTorneoDTO.getNomeTorneo() + "torneo"));
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

}
