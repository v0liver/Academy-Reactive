package it.reactive.torneoDemo.Mapper;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.GiocatoreResource;
import it.reactive.torneoDemo.resource.SquadraResource;

import java.util.HashSet;
import java.util.Set;

public class SquadraMapper {

    public static SquadraResource fromModelToResource(SquadraModel squadraModel) {
        SquadraResource squadraResource = new SquadraResource();
        squadraResource.setIdSquadra(squadraModel.getIdSquadra());
        squadraResource.setNome(squadraModel.getNome());
        squadraResource.setColoriSociali(squadraModel.getColoriSociali());
        if (squadraModel.getTifoseria()!= null) {
            squadraResource.setTifoseria(TifoseriaMapper.fromModelToResource(squadraModel.getTifoseria()));
        }
        Set<GiocatoreResource> listaGiocatori = new HashSet<>();
        if (squadraModel.getGiocatori()!=null) {
            for (GiocatoreModel giocatoreModel : squadraModel.getGiocatori()) {
                GiocatoreResource giocatoreResource = GiocatoreMapper.fromModelToResource(giocatoreModel);
                listaGiocatori.add(giocatoreResource);
            }

        }
        squadraResource.setGiocatori(listaGiocatori);
       //listaGiocatori= squadraModel.getGiocatori().stream().map(giocatoreModel->GiocatoreMapper.fromModelToResource(giocatoreModel)).collect(Collectors.toSet());;

        return squadraResource;

    }
}
