package it.reactive.torneoDemo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


public class SquadraModel {
    private Integer idSquadra;
    @NotBlank
    @Size(min = 3, max = 20, message = "Il nome della squadra deve essere almeno di 3 caratterie e massimo di 20")
    private String nome;
    @NotBlank
    private String coloriSociali;
    private Set<GiocatoreModel> giocatori;

    public TifoseriaModel getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaModel tifoseria) {
        this.tifoseria = tifoseria;
    }

    private TifoseriaModel tifoseria;

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

    public Set<GiocatoreModel> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreModel> giocatori) {
        this.giocatori = giocatori;
    }



    public Set<Torneo> getTornei() {
        return tornei;
    }

    public void setTornei(Set<Torneo> tornei) {
        this.tornei = tornei;
    }

    Set<Torneo> tornei;
}
