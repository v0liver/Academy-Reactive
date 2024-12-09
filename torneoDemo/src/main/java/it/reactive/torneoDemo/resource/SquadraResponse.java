package it.reactive.torneoDemo.resource;

import java.util.Set;

public class SquadraResponse {
    private Integer idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<GiocatoreResponse> giocatori;
    private TifoseriaResponse tifoseria;

    public SquadraResponse(Integer idSquadra, String nome, String coloriSociali, Set<GiocatoreResponse> giocatori, TifoseriaResponse tifoseria) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseria = tifoseria;
    }

    public SquadraResponse() {
    }

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
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

    public Set<GiocatoreResponse> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreResponse> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaResponse getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaResponse tifoseria) {
        this.tifoseria = tifoseria;
    }

}

