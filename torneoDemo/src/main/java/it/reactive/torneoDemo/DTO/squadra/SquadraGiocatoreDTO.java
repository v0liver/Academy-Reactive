package it.reactive.torneoDemo.DTO.squadra;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class SquadraGiocatoreDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;
    private String coloreSociale;
    private List<GiocatoreDto> giocatori;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColoreSociale() {
        return coloreSociale;
    }

    public void setColoreSociale(String coloreSociale) {
        this.coloreSociale = coloreSociale;
    }

    public List<GiocatoreDto> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(List<GiocatoreDto> giocatori) {
        this.giocatori = giocatori;
    }
}
