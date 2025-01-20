package it.reactive.demoTorneoSpringBatch.DTO;


import it.reactive.demoTorneoSpringBatch.model.TipoFile;

public class TorneoDTO extends TipoFile {


    private String nomeTorneo;

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }
}
