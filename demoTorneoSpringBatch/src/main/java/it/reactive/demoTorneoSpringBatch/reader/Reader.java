package it.reactive.demoTorneoSpringBatch.reader;

import it.reactive.demoTorneoSpringBatch.DTO.GiocatoreSquadraDTO;
import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Reader {

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

    @Bean(Costanti.Reader_Csv)
    public JdbcCursorItemReader<GiocatoreSquadraDTO> giocatoreReader(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<GiocatoreSquadraDTO>()
                .name(Costanti.Reader_Csv)
                .dataSource(dataSource)
                .sql("select g.*, s.nome, s.colori_sociali, t.nome_tifoseria from giocatore g join squadra s on g.id_squadra = s.id left join tifoseria t on s.id = t.id_squadra ")

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


}
