package it.reactive.torneoDemo.DTO.giocatore;


import javax.validation.constraints.NotBlank;

public class GiocatoreDto {


    @NotBlank(message = "Il nome non puo essere null")
    private String nomeCognome;

    public @NotBlank(message = "Il nome non puo essere null") String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(@NotBlank(message = "Il nome non puo essere null") String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public GiocatoreDto() {
    }
}
