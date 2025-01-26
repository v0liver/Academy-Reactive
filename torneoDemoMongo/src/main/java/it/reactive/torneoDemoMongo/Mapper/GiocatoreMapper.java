package it.reactive.torneoDemoMongo.Mapper;

import it.reactive.torneoDemoMongo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemoMongo.model.GiocatoreModel;
import it.reactive.torneoDemoMongo.repository.Trasferimenti;
import it.reactive.torneoDemoMongo.resource.GiocatoreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GiocatoreMapper {
    @Autowired
    Trasferimenti trasferimenti;

    public GiocatoreResource fromModelToResource(GiocatoreModel giocatoriModel) {
        GiocatoreResource giocatoreResource = new GiocatoreResource();

        giocatoreResource.setNomeCognome(giocatoriModel.getNomeCognome());
        giocatoreResource.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
        return giocatoreResource;
    }

    public GiocatoreResource fromModelToResourceTrasferimenti(GiocatoreModel giocatoriModel) {
        GiocatoreResource giocatoreResource = new GiocatoreResource();
        giocatoreResource.setNomeCognome(giocatoriModel.getNomeCognome());
        giocatoreResource.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
        giocatoreResource.setTrasferimenti(trasferimenti.trasferimenti(giocatoreResource.getNomeCognome()));
        return giocatoreResource;
    }

    public GiocatoreModel fromDtoToModel(GiocatoreDto giocatoreDto) {
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        giocatoreModel.setNomeCognome(giocatoreDto.getNomeCognome());
        return giocatoreModel;
    }

//    public static GiocatoreModel fromDtoToModel(GiocatoreDto giocatoreDto){
//        GiocatoreModel giocatoreModel = new GiocatoreModel();
//        giocatoreModel.setIdGiocatore(giocatoreDto.getIdGiocatore());
//        giocatoreResource.setNomeCognome(giocatoriModel.getNomeCognome());
//        giocatoreResource.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
//        return giocatoreResource;
//    }
}
