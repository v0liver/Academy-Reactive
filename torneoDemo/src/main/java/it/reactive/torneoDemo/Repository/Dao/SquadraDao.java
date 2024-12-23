package it.reactive.torneoDemo.Repository.Dao;

import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.model.SquadraModel;


public interface SquadraDao {

    SquadraModel salvaSquadra(SquadraDTO squadraDTO);
}
