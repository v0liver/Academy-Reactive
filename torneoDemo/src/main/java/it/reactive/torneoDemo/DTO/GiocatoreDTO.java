package it.reactive.torneoDemo.DTO;

import it.reactive.torneoDemo.resurce.Squadra;

import javax.validation.constraints.NotNull;

public class GiocatoreDTO {
    @NotNull
    String nomeCognome;

    public @NotNull String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(@NotNull String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }
}
