package it.reactive.demoTorneoSpringBatch.DTO;


import it.reactive.demoTorneoSpringBatch.model.TipoFile;

public class GiocatoreDto extends TipoFile {



    private String nomeCognome;

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public GiocatoreDto() {
    }
}
