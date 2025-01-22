package it.reactive.demoTorneoSpringBatch.writer;

import it.reactive.demoTorneoSpringBatch.DTO.SquadraDTO;
import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import it.reactive.demoTorneoSpringBatch.model.TipoFile;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component(Costanti.WRITER_SQUADRA_TIFOSERIA)
public class CustomWriterSquadraTifoseria implements ItemStreamWriter<TipoFile> {

    private final JdbcBatchItemWriter<TipoFile> writerSquadra;
    private final JdbcBatchItemWriter<TipoFile> writerTifoseria;

    public CustomWriterSquadraTifoseria(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        writerSquadra = new JdbcBatchItemWriterBuilder<TipoFile>().dataSource(dataSource)
                .itemPreparedStatementSetter((item, ps) -> {
                    SquadraDTO squadraDTO = (SquadraDTO) item;
                    ps.setInt(1, squadraDTO.getId());
                    ps.setString(2, squadraDTO.getNome());
                    ps.setString(3, squadraDTO.getColoriSociali());
                }).sql("insert into squadra(id, nome, colori_sociali) values (?,?,?)").build();
        writerTifoseria = new JdbcBatchItemWriterBuilder<TipoFile>().dataSource(dataSource)
                .itemPreparedStatementSetter((item, ps) -> {
                    SquadraDTO squadraDTO = (SquadraDTO) item;
                    ps.setString(1, squadraDTO.getTifoseria());
                    ps.setInt(2, squadraDTO.getId());
                }).sql("insert into tifoseria (nome_tifoseria, id_squadra) values (?,?)").build();
    }

    @Override
    public void write(Chunk<? extends TipoFile> chunk) throws Exception {
        chunk.forEach(tipoRecord -> {
            try {
                writerSquadra.write(Chunk.of(tipoRecord));
                SquadraDTO squadraDTO = (SquadraDTO) tipoRecord;
                if (!squadraDTO.getTifoseria().equals("-")) {
                    writerTifoseria.write(Chunk.of(tipoRecord));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}