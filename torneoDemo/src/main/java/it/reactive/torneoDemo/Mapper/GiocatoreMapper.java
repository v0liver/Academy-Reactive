package it.reactive.torneoDemo.Mapper;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.Repository.Trasferimenti;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.resource.GiocatoreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GiocatoreMapper {
    @Autowired
    Trasferimenti trasferimenti;

    public GiocatoreResource fromModelToResource(GiocatoreModel giocatoriModel) {
        GiocatoreResource giocatoreResource = new GiocatoreResource();
        giocatoreResource.setIdGiocatore(giocatoriModel.getIdGiocatore());
        giocatoreResource.setNomeCognome(giocatoriModel.getNomeCognome());
        giocatoreResource.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
        return giocatoreResource;
    }

    public GiocatoreResource fromModelToResourceTrasferimenti(GiocatoreModel giocatoriModel) {
        GiocatoreResource giocatoreResource = new GiocatoreResource();
        giocatoreResource.setIdGiocatore(giocatoriModel.getIdGiocatore());
        giocatoreResource.setNomeCognome(giocatoriModel.getNomeCognome());
        giocatoreResource.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
        giocatoreResource.setTrasferimenti(trasferimenti.trasferimenti(giocatoreResource.getNomeCognome()));
        return giocatoreResource;
    }

//    public static GiocatoreModel fromDtoToModel(GiocatoreDto giocatoreDto){
//        GiocatoreModel giocatoreModel = new GiocatoreModel();
//        giocatoreModel.setIdGiocatore(giocatoreDto.getIdGiocatore());
//        giocatoreResource.setNomeCognome(giocatoriModel.getNomeCognome());
//        giocatoreResource.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
//        return giocatoreResource;
//    }
}
