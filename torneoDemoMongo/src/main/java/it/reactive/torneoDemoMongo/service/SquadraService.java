package it.reactive.torneoDemoMongo.service;


import it.reactive.torneoDemoMongo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemoMongo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemoMongo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemoMongo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemoMongo.Mapper.GiocatoreMapper;
import it.reactive.torneoDemoMongo.Mapper.SquadraMapper;
import it.reactive.torneoDemoMongo.Mapper.TifoseriaMapper;
import it.reactive.torneoDemoMongo.exception.SquadraDuplicataException;
import it.reactive.torneoDemoMongo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemoMongo.model.GiocatoreModel;
import it.reactive.torneoDemoMongo.model.SquadraModel;
import it.reactive.torneoDemoMongo.repository.SquadraDao;
import it.reactive.torneoDemoMongo.resource.SquadraResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SquadraService {
    @Autowired
    SquadraDao squadraDao;
    @Autowired
    SquadraMapper squadraMapper;
    @Autowired
    GiocatoreMapper giocatoreMapper;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    TifoseriaMapper tifoseriaMapper;


    public SquadraResource salvaSquadra(SquadraDTO squadraDTO) {
        SquadraModel squadraModel = squadraDao.findByNome(squadraDTO.getNome());
        if (squadraModel == null) {
            return squadraMapper.fromModelToResourceWithouthGiocatori(squadraDao.salvaSquadra(squadraDTO));
        } else throw new SquadraDuplicataException();
    }

    public SquadraResource aggiungiGiocatore(GiocatoreDto giocatoreDto, String nomeSquadra) {
        SquadraModel squadraModel = squadraDao.findByNome(nomeSquadra);
        if (squadraModel != null) {
            squadraModel.getGiocatori().add(giocatoreMapper.fromDtoToModel(giocatoreDto));
            return squadraMapper.fromModelToResource(mongoTemplate.save(squadraModel));
        } else {
            throw new SquadraNonPresenteException();
        }

    }

    public SquadraResource salvaSquadraCongiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) {
        SquadraModel squadraModel = squadraDao.findByNome(squadreDiGiocatoriDTO.getNome());
        List<GiocatoreModel> giocatoreModelList = new ArrayList<>();
        if (squadraModel != null) {
            throw new SquadraDuplicataException();
        } else {
            squadraModel = new SquadraModel();
            for (GiocatoreDto giocatoreDto : squadreDiGiocatoriDTO.getListaGiocatori()) {
                giocatoreModelList.add(giocatoreMapper.fromDtoToModel(giocatoreDto));
            }
            squadraModel.setGiocatori(giocatoreModelList);

            return squadraMapper.fromModelToResource(mongoTemplate.save(squadraModel));
        }
    }

    public List<SquadraResource> ricercaSquadre(boolean completo) {
        List<SquadraModel> squadraModelList = mongoTemplate.findAll(SquadraModel.class);
        if (completo) {
            return squadraModelList.stream().map(squadraModel -> squadraMapper.fromModelToResource(squadraModel)).collect(Collectors.toList());
        } else {
            return squadraModelList.stream().map(squadraModel -> squadraMapper.fromModelToResourceWithouthGiocatori(squadraModel)).collect(Collectors.toList());
        }

    }

    public SquadraResource addTifoseria(TifoseriaDTO tifoseriaDTO, String nomeSquadra) {
        SquadraModel squadraModel = squadraDao.findByNome(nomeSquadra);
        if (squadraModel != null) {
            squadraModel.setTifoseria(tifoseriaMapper.fromDtoToModel(tifoseriaDTO));

            return squadraMapper.fromModelToResource(mongoTemplate.save(squadraModel));
        } else throw new SquadraNonPresenteException();
    }

    public void deleteSquadra(String nomeSquadra) {
        squadraDao.deleteSquadra(nomeSquadra);
    }

}
