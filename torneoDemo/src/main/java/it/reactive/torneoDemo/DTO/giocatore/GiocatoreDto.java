package it.reactive.torneoDemo.DTO.giocatore;


import javax.validation.constraints.NotBlank;

public class GiocatoreDto {

    @NotBlank(message = "Il nome non puo essere null")
    private String nome;

    @NotBlank(message = "Il nome non puo essere null")
    private String cognome;

    public String getNomeCognome() {
        return nome;
    }

    public void setNomeCognome(String nome) {
        this.nome = nome;
    }
}
