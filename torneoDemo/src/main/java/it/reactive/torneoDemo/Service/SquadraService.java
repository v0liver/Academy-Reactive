package it.reactive.torneoDemo.Service;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Repository.Dao.SquadraDao;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.Mapper.SquadraMapper;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.SquadraResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SquadraService {
    @Autowired
    SquadraDao squadraDao;

    public SquadraResource salvaSquadra(SquadraDTO squadraDTO) {

        SquadraModel squadraModel = squadraDao.salvaSquadra(squadraDTO);

        return SquadraMapper.fromModelToResource(squadraModel);
    }

    public SquadraResource salvaSquadraSquadraGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) {

        SquadraModel squadraModel = squadraDao.salvaSquadraSquadraGiocatori(squadreDiGiocatoriDTO);

        return SquadraMapper.fromModelToResource(squadraModel);
    }

    public List<SquadraResource> ricercaSquadra(boolean completo) {
        List<SquadraModel> squadraModel = squadraDao.ricercaSquadra(completo);
        List<SquadraResource> squadraResource = new ArrayList<>();
        for (SquadraModel model : squadraModel) {
            squadraResource.add(SquadraMapper.fromModelToResource(model));
        }
        return squadraResource;
    }

    public SquadraResource aggiungiGiocatore(int id, GiocatoreDto giocatoreDto){
        SquadraModel squadraModel = squadraDao.aggiungiGiocatore(id,giocatoreDto);
        return SquadraMapper.fromModelToResource(squadraModel);
    }

    public SquadraResource aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO){
        SquadraModel squadraModel = squadraDao.aggiungiTifoseria(idSquadra,tifoseriaDTO);
        return SquadraMapper.fromModelToResource(squadraModel);
    }

    public void rimuoviSquadra(int idSquadra){
        squadraDao.rimuoviSquadra(idSquadra);
    }
}
