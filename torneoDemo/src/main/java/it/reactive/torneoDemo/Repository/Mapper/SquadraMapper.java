package it.reactive.torneoDemo.Repository.Mapper;

import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.GiocatoreResource;
import it.reactive.torneoDemo.resource.SquadraResource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SquadraMapper {

    public static SquadraResource fromModelToResource(SquadraModel squadraModel) {
        SquadraResource squadraResource = new SquadraResource();
        squadraResource.setIdSquadra(squadraModel.getIdSquadra());
        squadraResource.setNome(squadraModel.getNome());
        squadraResource.setColoriSociali(squadraModel.getColoriSociali());
        squadraResource.setTifoseria(TifoseriaMapper.fromModelToResource(squadraModel.getTifoseria()));
        Set<GiocatoreResource> listaGiocatori = new HashSet<>();
        for (GiocatoreModel giocatoreModel : squadraModel.getGiocatori()) {
           GiocatoreResource giocatoreResource = GiocatoreMapper.fromModelToResource(giocatoreModel);
            listaGiocatori.add(giocatoreResource);
        }
       // listaGiocatori= squadraModel.getGiocatori().stream().map(giocatoreModel->GiocatoreMapper.fromModelToResource(giocatoreModel)).collect(Collectors.toSet());;
        squadraResource.setGiocatori(listaGiocatori);
        return squadraResource;

    }
}
