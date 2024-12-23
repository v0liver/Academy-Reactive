package it.reactive.torneoDemo.resource;

import java.util.HashSet;
import java.util.Set;

public class GiocatoreResource {
    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    private Set<TrasferimentiResource> trasferimentiResource = new HashSet<>();

    public GiocatoreResource(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni, Set<TrasferimentiResource> trasferimentiResource) {
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.trasferimentiResource = trasferimentiResource;
    }

    public GiocatoreResource() {
    }

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

    public Set<TrasferimentiResource> getTrasferimenti() {
        return trasferimentiResource;
    }

    public void setTrasferimenti(Set<TrasferimentiResource> trasferimentiResource) {
        this.trasferimentiResource = trasferimentiResource;
    }
}

