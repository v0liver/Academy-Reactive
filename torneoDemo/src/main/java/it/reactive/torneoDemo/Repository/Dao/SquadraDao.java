package it.reactive.torneoDemo.Repository.Dao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;

import java.util.List;
import java.util.Set;


public interface SquadraDao {

    SquadraModel salvaSquadra(SquadraDTO squadraDTO);
    SquadraModel getSquadraById(int idSquadra);
    Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra);
    List<SquadraModel> ricercaSquadra(boolean completo);
    SquadraModel aggiungiGiocatore (int idSquadra, GiocatoreDto giocatoreDto);
    SquadraModel aggiungiTifoseria (int idSquadra, TifoseriaDTO tifoseriaDTO);
    void rimuoviSquadra(int idSquadra);
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra);
}
