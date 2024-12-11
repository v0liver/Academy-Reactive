package it.reactive.torneoDemo.DTO.squadra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SquadraDTO {

    @NotBlank
    @Size(min = 3, max = 20, message = "Il nome della squadra deve essere almeno di 3 caratterie e massimo di 20")
    private String nome;
    @NotBlank
    private String coloriSociali;


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
}
