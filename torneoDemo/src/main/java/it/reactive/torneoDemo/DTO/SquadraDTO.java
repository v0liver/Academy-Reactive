package it.reactive.torneoDemo.DTO;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SquadraDTO {
    @NotNull
    @Size(min = 3, message = "Caratteri minimi 3")
    String nome;
    String coloriSociali;

    public @NotNull @Size(min = 3, message = "Caratteri minimi 3") String getNome() {
        return nome;
    }

    public void setNome(@NotNull @Size(min = 3, message = "Caratteri minimi 3") String nome) {
        this.nome = nome;
    }

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }
}
