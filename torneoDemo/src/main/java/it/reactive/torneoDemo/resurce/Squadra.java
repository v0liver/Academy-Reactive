package it.reactive.torneoDemo.resurce;

import java.util.Set;

public class Squadra {
    Integer idSquadra;
    String nome;
    String coloriSociali;
    Set<Giocatore> giocatori;
    Tifoseria tifoseria;

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

    public Set<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    public Tifoseria getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(Tifoseria tifoseria) {
        this.tifoseria = tifoseria;
    }

    public Set<Torneo> getTornei() {
        return tornei;
    }

    public Squadra() {
    }

    public void setTornei(Set<Torneo> tornei) {
        this.tornei = tornei;
    }

    Set<Torneo> tornei;// ---> LASCIARLO NEL MODEL

    public Squadra(Integer idSquadra, String nome, String coloriSociali, Set<Giocatore> giocatori, Tifoseria tifoseria, Set<Torneo> tornei) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseria = tifoseria;
        this.tornei = tornei;
    }
}
