package it.reactive.torneoDemo.DTO;

import it.reactive.torneoDemo.resurce.Giocatore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class SquadraGiocatoriDTO {
    @NotNull
    @Size(min = 3,message = "Caratteri minimi 3")
    String nome;
    String coloriSociali;
    Set<Giocatore> giocatori;

    public Set<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

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
