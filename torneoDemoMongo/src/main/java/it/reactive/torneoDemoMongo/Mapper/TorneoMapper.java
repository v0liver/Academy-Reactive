package it.reactive.torneoDemoMongo.Mapper;


import it.reactive.torneoDemoMongo.model.TorneoModel;
import it.reactive.torneoDemoMongo.resource.TorneoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TorneoMapper {

    @Autowired
    SquadraMapper squadraMapper;

    public TorneoResource fromModelToResource(TorneoModel torneoModel) {
        TorneoResource torneoResource = new TorneoResource();
        torneoResource.setIdTorneo(String.valueOf(torneoModel.get_id()));
        torneoResource.setNomeTorneo(torneoModel.getNomeTorneo());

        torneoResource.setSquadre(torneoModel.getSquadre());
        return torneoResource;
    }
}
