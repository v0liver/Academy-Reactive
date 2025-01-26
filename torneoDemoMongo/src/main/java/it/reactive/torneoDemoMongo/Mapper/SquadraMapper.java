package it.reactive.torneoDemoMongo.Mapper;


import it.reactive.torneoDemoMongo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemoMongo.model.GiocatoreModel;
import it.reactive.torneoDemoMongo.model.SquadraModel;
import it.reactive.torneoDemoMongo.resource.GiocatoreResource;
import it.reactive.torneoDemoMongo.resource.SquadraResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SquadraMapper {

    @Autowired
    TifoseriaMapper tifoseriaMapper;

    @Autowired
    GiocatoreMapper giocatoreMapper;


    public SquadraResource fromModelToResource(SquadraModel squadraModel) {
        SquadraResource squadraResource = new SquadraResource();
        squadraResource.setIdSquadra(String.valueOf(squadraModel.get_id()));
        squadraResource.setNome(squadraModel.getNome());
        squadraResource.setColoriSociali(squadraModel.getColoriSociali());
        if (squadraModel.getTifoseria() != null) {
            squadraResource.setTifoseria(tifoseriaMapper.fromModelToResource(squadraModel.getTifoseria()));
        }
        Set<GiocatoreResource> listaGiocatori = new HashSet<>();
        if (squadraModel.getGiocatori() != null) {
            for (GiocatoreModel giocatoreModel : squadraModel.getGiocatori()) {
                GiocatoreResource giocatoreResource = giocatoreMapper.fromModelToResource(giocatoreModel);
                listaGiocatori.add(giocatoreResource);
            }

        }
        squadraResource.setGiocatori(listaGiocatori);
        //listaGiocatori= squadraModel.getGiocatori().stream().map(giocatoreModel->giocatoreMapper.fromModelToResource(giocatoreModel)).collect(Collectors.toSet());

        return squadraResource;

    }


    public SquadraResource fromModelToResourceWithouthGiocatori(SquadraModel squadraModel) {
        SquadraResource squadraResource = new SquadraResource();
        squadraResource.setIdSquadra(String.valueOf(squadraModel.get_id()));
        squadraResource.setNome(squadraModel.getNome());
        squadraResource.setColoriSociali(squadraModel.getColoriSociali());
//        if (squadraModel.getTifoseria() != null) {
//            squadraResource.setTifoseria(tifoseriaMapper.fromModelToResource(squadraModel.getTifoseria()));
//        }
        return squadraResource;
    }

    public SquadraModel fromDtoToModel(SquadraDTO squadraDTO) {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
        squadraModel.setNome(squadraDTO.getNome());
        return squadraModel;
    }
}
