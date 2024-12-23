package it.reactive.torneoDemo.DAO.SquadraDao;

import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.SquadraResponse;

public interface SquadraDao {

    SquadraModel salvaSquadra(SquadraDTO squadraDTO);
}
