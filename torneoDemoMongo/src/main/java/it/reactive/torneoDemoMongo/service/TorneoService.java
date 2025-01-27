package it.reactive.torneoDemoMongo.service;

import it.reactive.torneoDemoMongo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemoMongo.Mapper.TorneoMapper;
import it.reactive.torneoDemoMongo.exception.*;
import it.reactive.torneoDemoMongo.model.SquadraModel;
import it.reactive.torneoDemoMongo.model.TorneoModel;
import it.reactive.torneoDemoMongo.repository.SquadraDao;
import it.reactive.torneoDemoMongo.repository.TorneoDao;
import it.reactive.torneoDemoMongo.resource.TorneoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TorneoService {
    @Autowired
    TorneoDao torneoDao;
    @Autowired
    TorneoMapper torneoMapper;
    @Autowired
    SquadraDao squadraDao;
    @Autowired
    MongoTemplate mongoTemplate;

    public TorneoResource addTorneo(TorneoDTO torneoDTO) {
        TorneoModel torneoModel = torneoDao.findByNome(torneoDTO.getNomeTorneo());
        if (torneoModel != null) {
            throw new TorneoDuplicatoException();
        } else {
            return torneoMapper.fromModelToResource(torneoDao.addTorneo(torneoDTO));
        }
    }

    public TorneoResource censitaSquadraTorneo(String nomeTorneo, String nomeSquadra) {
        TorneoModel torneoModel = torneoDao.findByNome(nomeTorneo);

        if (torneoModel != null) {
            SquadraModel squadraModel = squadraDao.findByNome(nomeSquadra);
            if (squadraModel != null) {
                if (torneoModel.getSquadre()==null) {
                    torneoModel.setSquadre(Arrays.asList(nomeSquadra));
                }else {
                    torneoModel.getSquadre().add(nomeSquadra);
                }
                return torneoMapper.fromModelToResource(mongoTemplate.save(torneoModel));
            } else throw new SquadraNonPresenteException();

        } else throw new TorneoNonTrovatoException();
    }

    public List<TorneoResource> getTorneoAndSquadre() {
        List<TorneoResource> torneoResourceList = new ArrayList<>();
        for (TorneoModel torneoModel : mongoTemplate.findAll(TorneoModel.class)) {
            torneoResourceList.add(torneoMapper.fromModelToResource(torneoModel));
        }
        return torneoResourceList;
    }

    public List<TorneoResource> removeTorneoAndSquadre(String nomeTorneo) {
        TorneoModel torneoModel = torneoDao.findByNome(nomeTorneo);
        List<String> squadre = torneoModel.getSquadre();
        if (squadre != null && squadre.size() == 1) {
            for (String squadra : squadre) {
                Query query = new Query(Criteria.where("squadre").is(squadra).and("nomeTorneo").ne(nomeTorneo));
                TorneoModel torneo = mongoTemplate.findOne(query, TorneoModel.class);
                if (torneo != null) {
                    throw new SquadraPresenteInAltriTorneiException();
                } else {
                    squadraDao.deleteSquadra(squadra);
                }

            }

        } else if (squadre != null && squadre.size() > 1) {
            throw new TorneoConPiuSquadreException();
        }

        torneoDao.deleteTorneo(nomeTorneo);
        return getTorneoAndSquadre();

    }
}
