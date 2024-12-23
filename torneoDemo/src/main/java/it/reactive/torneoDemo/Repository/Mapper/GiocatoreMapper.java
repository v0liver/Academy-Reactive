package it.reactive.torneoDemo.Repository.Mapper;

import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.resource.GiocatoreResource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiocatoreMapper {

    public static GiocatoreResource fromModelToResource(GiocatoreModel giocatoriModel){
        GiocatoreResource giocatoreResource = new GiocatoreResource();
        giocatoreResource.setIdGiocatore(giocatoriModel.getIdGiocatore());
        giocatoreResource.setNomeCognome(giocatoriModel.getNomeCognome());
        giocatoreResource.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
        return giocatoreResource;
    }
}
