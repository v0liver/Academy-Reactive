package it.reactive.torneoDemoMongo.repository;

import it.reactive.torneoDemoMongo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemoMongo.model.TorneoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class TorneoDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public TorneoModel addTorneo(TorneoDTO torneoDTO) {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(torneoDTO.getNomeTorneo());
        return mongoTemplate.insert(torneoModel);
    }

    public TorneoModel findByNome(String nomeTorneo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nomeTorneo").is(nomeTorneo));
        return mongoTemplate.findOne(query, TorneoModel.class);
    }

    public void deleteTorneo(String nomeTorneo) {
        TorneoModel torneoModel = findByNome(nomeTorneo);
        mongoTemplate.remove(torneoModel);
    }

}
