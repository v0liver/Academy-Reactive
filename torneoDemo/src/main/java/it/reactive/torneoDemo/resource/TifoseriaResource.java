package it.reactive.torneoDemo.resource;

public class TifoseriaResponse {
    private Integer idTifoseria;
    private String nomeTifoseria;

    public TifoseriaResponse(Integer idTifoseria, String nomeTifoseria) {
        this.idTifoseria = idTifoseria;
        this.nomeTifoseria = nomeTifoseria;
    }

    public Integer getIdTifoseria() {
        return idTifoseria;
    }

    public void setIdTifoseria(Integer idTifoseria) {
        this.idTifoseria = idTifoseria;
    }

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

}

