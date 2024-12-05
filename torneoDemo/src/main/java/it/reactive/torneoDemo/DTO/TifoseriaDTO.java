package it.reactive.torneoDemo.DTO;

import it.reactive.torneoDemo.resurce.Squadra;

import javax.validation.constraints.NotNull;

public class TifoseriaDTO {

    @NotNull
    String nomeTifoseria;

    public @NotNull String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(@NotNull String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }
}
