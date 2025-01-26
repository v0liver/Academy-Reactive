package it.reactive.torneoDemoMongo.DTO.squadra;

import it.reactive.torneoDemoMongo.DTO.giocatore.GiocatoreDto;

import java.util.List;

public class SquadreDiGiocatoriDTO extends SquadraDTO {


    private String nome;

    private String coloriSociali;

    private List<GiocatoreDto> listaGiocatori;

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

    public List<GiocatoreDto> getListaGiocatori() {
        return listaGiocatori;
    }

    public void setListaGiocatori(List<GiocatoreDto> listaGiocatori) {
        this.listaGiocatori = listaGiocatori;
    }
}
