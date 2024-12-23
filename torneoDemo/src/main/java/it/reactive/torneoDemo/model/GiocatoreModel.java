package it.reactive.torneoDemo.model;

import it.reactive.torneoDemo.resource.TrasferimentiResource;

import java.util.HashSet;
import java.util.Set;

public class GiocatoreModel {
    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    private SquadraModel squadraModel;

    public Integer getIdGiocatore() {
        return idGiocatore;
    }

    public void setIdGiocatore(Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public Integer getNumeroAmmonizioni() {
        return numeroAmmonizioni;
    }

    public void setNumeroAmmonizioni(Integer numeroAmmonizioni) {
        this.numeroAmmonizioni = numeroAmmonizioni;
    }

    public SquadraModel getSquadraModel() {
        return squadraModel;
    }

    public void setSquadraModel(SquadraModel squadraModel) {
        this.squadraModel = squadraModel;
    }
}
