package it.reactive.torneoDemo.model;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.resource.GiocatoreResponse;
import it.reactive.torneoDemo.resource.TifoseriaResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class Squadra {
    private Integer idSquadra;
    @NotBlank
    @Size(min = 3, max = 20, message = "Il nome della squadra deve essere almeno di 3 caratterie e massimo di 20")
    private String nome;
    @NotBlank
    private String coloriSociali;
    private Set<GiocatoreResponse> giocatori;
    private TifoseriaResponse tifoseria;

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

    public Set<Torneo> getTornei() {
        return tornei;
    }

    public void setTornei(Set<Torneo> tornei) {
        this.tornei = tornei;
    }

    Set<Torneo> tornei;
}
