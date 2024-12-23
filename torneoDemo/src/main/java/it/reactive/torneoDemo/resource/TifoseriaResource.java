package it.reactive.torneoDemo.resource;

public class TifoseriaResource {
    private Integer idTifoseria;
    private String nomeTifoseria;

    public TifoseriaResource(Integer idTifoseria, String nomeTifoseria) {
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

