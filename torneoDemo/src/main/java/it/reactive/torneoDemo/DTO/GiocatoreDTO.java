package it.reactive.torneoDemo.DTO;

import it.reactive.torneoDemo.resurce.Squadra;

import javax.validation.constraints.NotNull;

public class GiocatoreDTO {
    @NotNull
    String nome;
    @NotNull
    String cognome;

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getCognome() {
        return cognome;
    }

    public void setCognome(@NotNull String cognome) {
        this.cognome = cognome;
    }
}
