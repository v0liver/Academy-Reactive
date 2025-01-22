package it.reactive.demoTorneoSpringBatch.DTO;


import it.reactive.demoTorneoSpringBatch.model.TipoFile;

public class TorneoDTO extends TipoFile {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String nomeTorneo;

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }
}
