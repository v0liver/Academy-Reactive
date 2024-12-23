package it.reactive.torneoDemo.model;

public class TifoseriaModel {
    private Integer idTifoseria;
    private String nomeTifoseria;
    private SquadraModel squadraModel;

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

    public SquadraModel getSquadraModel() {
        return squadraModel;
    }

    public void setSquadraModel(SquadraModel squadraModel) {
        this.squadraModel = squadraModel;
    }
}
