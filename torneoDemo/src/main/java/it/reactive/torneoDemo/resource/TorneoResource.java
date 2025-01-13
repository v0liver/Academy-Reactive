package it.reactive.torneoDemo.resource;

import java.util.List;
import java.util.Set;

public class TorneoResource {
    private Integer idTorneo;
    String nomeTorneo;
    List<SquadraResource> squadre;


    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public List<SquadraResource> getSquadre() {
        return squadre;
    }

    public void setSquadre(List<SquadraResource> squadre) {
        this.squadre = squadre;
    }
}
