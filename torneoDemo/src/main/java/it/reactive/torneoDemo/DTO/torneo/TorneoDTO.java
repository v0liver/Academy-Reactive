package it.reactive.torneoDemo.DTO.torneo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TorneoDTO {

    @NotBlank
    @Size(min = 3, message = "Il nome del torneo deve essere lungo almeno 3")
    private String nomeTorneo;

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }
}
