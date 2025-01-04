package it.reactive.torneoDemo.Repository.Dao;

import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.model.SquadraModel;

import java.util.List;


public interface SquadraDao {

    SquadraModel salvaSquadra(SquadraDTO squadraDTO);
    SquadraModel salvaSquadraSquadraGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO);
    List<SquadraModel> ricercaSquadra(boolean completo);

}
