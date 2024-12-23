package it.reactive.torneoDemo.Repository.Mapper;

import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.SquadraResource;

public class SquadraMapper {

    public static SquadraResource fromModelToResponse(SquadraModel squadraModel){
        SquadraResource squadraResource = new SquadraResource();
        squadraResource.setIdSquadra(squadraModel.getIdSquadra());
        squadraResource.setNome(squadraModel.getNome());
        squadraResource.setColoriSociali(squadraModel.getColoriSociali());
//        squadraResponse.setTifoseria(squadraModel.getTifoseria());
//        squadraResponse.setGiocatori(squadraModel.getGiocatori());
        return squadraResource;

    }
}
