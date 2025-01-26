package it.reactive.torneoDemoMongo.resource;

import java.util.Set;

public class SquadraResource {
    private String idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<GiocatoreResource> giocatori;
    private TifoseriaResource tifoseria;


    public String getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(String idSquadra) {
        this.idSquadra = idSquadra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public Set<GiocatoreResource> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreResource> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaResource getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaResource tifoseria) {
        this.tifoseria = tifoseria;
    }

}

