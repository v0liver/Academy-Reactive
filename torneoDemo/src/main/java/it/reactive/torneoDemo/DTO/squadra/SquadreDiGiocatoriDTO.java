package it.reactive.torneoDemo.DTO.squadra;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SquadraGiocatoreDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;
    @NotBlank
    private String coloriSociali;
    @NotNull
    @Valid
    private List<GiocatoreDto> giocatori;

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

    public List<GiocatoreDto> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(List<GiocatoreDto> giocatori) {
        this.giocatori = giocatori;
    }
}
