package it.reactive.torneoDemo.Mapper;

import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.resource.SquadraResource;
import it.reactive.torneoDemo.resource.TifoseriaResource;
import it.reactive.torneoDemo.resource.TorneoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TorneoMapper {

    @Autowired
    SquadraMapper squadraMapper;

    public TorneoResource fromModelToResource(TorneoModel torneoModel) {
        TorneoResource torneoResource = new TorneoResource();
        torneoResource.setIdTorneo(torneoModel.getIdTorneo());
        torneoResource.setNomeTorneo(torneoModel.getNomeTorneo());
        List<SquadraResource> squadraResources = new ArrayList<>();
        for (SquadraModel squadraModel : torneoModel.getSquadre()) {
            squadraResources.add(squadraMapper.fromModelToResource(squadraModel));
        }
        torneoResource.setSquadre(squadraResources);
        return torneoResource;
    }
}
