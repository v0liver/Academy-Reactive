package it.reactive.torneoDemo.SquadraTest;

import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.Repository.Dao.SquadraDao.SquadraDao;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.SquadraModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class SquadraTest {

    @Autowired
    SquadraDao squadraDao;

    @Test
    void salvaSquadraException() {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setNome("Juventus");
        squadraDTO.setColoriSociali("Bianco e Nero");
        assertThrows(SquadraDuplicataException.class,()->squadraDao.salvaSquadra(squadraDTO));

    }
}
