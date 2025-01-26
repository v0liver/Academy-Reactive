package it.reactive.torneoDemoMongo.repository;

import it.reactive.torneoDemoMongo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemoMongo.Mapper.GiocatoreMapper;
import it.reactive.torneoDemoMongo.model.SquadraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SquadraDao {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    GiocatoreMapper giocatoreMapper;


    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(squadraDTO.getNome());
        squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
        mongoTemplate.insert(squadraModel);
        return squadraModel;
    }

    public SquadraModel findByNome(String nomeSquadra) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(nomeSquadra));
        return mongoTemplate.findOne(query, SquadraModel.class);
    }

    public void deleteSquadra(String nomeSquadra) {
        SquadraModel squadraModel = findByNome(nomeSquadra);
        mongoTemplate.remove(squadraModel);
    }


}
