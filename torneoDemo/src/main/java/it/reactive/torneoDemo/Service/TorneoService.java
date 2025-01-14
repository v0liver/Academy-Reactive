package it.reactive.torneoDemo.Service;

import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.Mapper.TorneoMapper;
import it.reactive.torneoDemo.Repository.Dao.TorneoDao.TorneoDao;
import it.reactive.torneoDemo.Repository.Trasferimenti;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.resource.GiocatoreResource;
import it.reactive.torneoDemo.resource.SquadraResource;
import it.reactive.torneoDemo.resource.TorneoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TorneoService   {

    @Autowired
    TorneoDao torneoDao;
    @Autowired
    TorneoMapper torneoMapper;
    @Autowired
    Trasferimenti trasferimenti;

    public TorneoResource aggiungiTorneo(TorneoDTO torneoDTO) {
        return torneoMapper.fromModelToResource(torneoDao.aggiungiTorneo(torneoDTO));
    }


    public TorneoResource censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {

        return  torneoMapper.fromModelToResource(torneoDao.censitaSquadraAlTorneo(idTorneo,idSquadra)) ;
    }


    public List<TorneoResource> getTorneoEndSquadre() {
        List<TorneoResource> torneoResourceList = new ArrayList<>();

        for (TorneoModel torneoModel : torneoDao.getTorneoEndSquadre()) {
            torneoResourceList.add(torneoMapper.fromModelToResource(torneoModel));
        }
        for (TorneoResource torneoResource : torneoResourceList) {
            for (SquadraResource squadraResource : torneoResource.getSquadre()) {
                for (GiocatoreResource giocatoreResource : squadraResource.getGiocatori()) {
                    giocatoreResource.setTrasferimenti(trasferimenti.trasferimenti(giocatoreResource.getNomeCognome()));
                }
            }
        }
        
        return torneoResourceList;
    }


    public List<TorneoResource> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        List<TorneoModel> torneoModelList = torneoDao.eliminaTorneoConSquadreAndGiocatori(idTorneo);
       List<TorneoResource> torneoResourceList = new ArrayList<>();
        for (TorneoModel torneoModel : torneoModelList) {
           torneoResourceList.add(torneoMapper.fromModelToResource(torneoModel));
        }
        return torneoResourceList;
    }

}
