package it.reactive.torneoDemo.Repository.Dao.TorneoDao;

import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.resource.TorneoResource;

import java.util.List;

public interface TorneoDao {
    TorneoModel aggiungiTorneo(TorneoDTO torneoDTO);
    TorneoModel censitaSquadraAlTorneo(Integer idTorneo,Integer idSquadra);
    List<TorneoModel> getTorneoEndSquadre();
    List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo);
    TorneoModel getTorneoById(Integer idTorneo);
    List<SquadraModel> getSquadreByIdTorneo(Integer idTorneo);
    List<TorneoModel> getTorneiByIdSquadra(Integer idSuadra);

}
