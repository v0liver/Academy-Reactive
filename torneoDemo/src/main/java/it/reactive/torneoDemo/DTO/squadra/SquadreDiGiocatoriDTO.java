package it.reactive.torneoDemo.DTO.squadra;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SquadreDiGiocatoriDTO extends SquadraDTO {

    @NotNull
    @Valid
    private List<GiocatoreDto> listaGiocatori;

    public List<GiocatoreDto> getListaGiocatori() {
        return listaGiocatori;
    }

    public void setListaGiocatori(List<GiocatoreDto> listaGiocatori) {
        this.listaGiocatori = listaGiocatori;
    }
}
