package it.reactive.torneoDemo.DTO;


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
