package it.reactive.torneoDemo.Mapper;

import it.reactive.torneoDemo.Repository.Trasferimenti;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.GiocatoreResource;
import it.reactive.torneoDemo.resource.SquadraResource;
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
        squadraResource.setIdSquadra(squadraModel.getIdSquadra());
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
        //listaGiocatori= squadraModel.getGiocatori().stream().map(giocatoreModel->GiocatoreMapper.fromModelToResource(giocatoreModel)).collect(Collectors.toSet());;

        return squadraResource;

    }


    public SquadraResource fromModelToResourceWithouthGiocatori(SquadraModel squadraModel) {
        SquadraResource squadraResource = new SquadraResource();
        squadraResource.setIdSquadra(squadraModel.getIdSquadra());
        squadraResource.setNome(squadraModel.getNome());
        squadraResource.setColoriSociali(squadraModel.getColoriSociali());
        if (squadraModel.getTifoseria() != null) {
            squadraResource.setTifoseria(tifoseriaMapper.fromModelToResource(squadraModel.getTifoseria()));
        }
        return squadraResource;
    }
}
