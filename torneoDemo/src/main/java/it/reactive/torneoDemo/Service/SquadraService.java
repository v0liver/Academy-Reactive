package it.reactive.torneoDemo.Service;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Mapper.GiocatoreMapper;
import it.reactive.torneoDemo.Mapper.TifoseriaMapper;
import it.reactive.torneoDemo.Repository.Dao.SquadraDao;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.Mapper.SquadraMapper;
import it.reactive.torneoDemo.exception.GiocatoreDuplicatoException;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.SquadraResource;
import it.reactive.torneoDemo.resource.TifoseriaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SquadraService {
    @Autowired
    SquadraDao squadraDao;

    @Autowired
    SquadraMapper squadraMapper;


    @Transactional
    public SquadraResource salvaSquadra(SquadraDTO squadraDTO) {

        SquadraModel squadraModel = squadraDao.salvaSquadra(squadraDTO);

        return squadraMapper.fromModelToResource(squadraModel);
    }

    @Transactional
    public SquadraResource salvaSquadraSquadraGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) {
        SquadraModel squadraModel = squadraDao.salvaSquadra(squadreDiGiocatoriDTO);
        for (GiocatoreDto giocatoreDto : squadreDiGiocatoriDTO.getListaGiocatori()) {
            squadraModel = squadraDao.aggiungiGiocatore(squadraModel.getIdSquadra(), giocatoreDto);
        }
        return squadraMapper.fromModelToResource(squadraModel);
    }

    @Transactional
    public List<SquadraResource> ricercaSquadra(boolean completo) {
        List<SquadraModel> squadraModel = squadraDao.ricercaSquadra(completo);
        List<SquadraResource> squadraResource = new ArrayList<>();
        for (SquadraModel model : squadraModel) {
            if (completo){
                squadraResource.add(squadraMapper.fromModelToResource(model));
            }else {
                squadraResource.add(squadraMapper.fromModelToResourceWithouthGiocatori(model));
            }
        }
        return squadraResource;
    }

    @Transactional
    public SquadraResource aggiungiGiocatore(int id, GiocatoreDto giocatoreDto) {
        SquadraModel squadraModel = squadraDao.aggiungiGiocatore(id, giocatoreDto);
        return squadraMapper.fromModelToResource(squadraModel);
    }

    @Transactional
    public SquadraResource aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        SquadraModel squadraModel = squadraDao.aggiungiTifoseria(idSquadra, tifoseriaDTO);
        return squadraMapper.fromModelToResource(squadraModel);
    }

    @Transactional
    public void rimuoviSquadra(int idSquadra) {
        squadraDao.rimuoviSquadra(idSquadra);
    }
}
