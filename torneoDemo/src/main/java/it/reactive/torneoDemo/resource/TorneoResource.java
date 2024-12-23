package it.reactive.torneoDemo.resource;

import java.util.Set;

public class TorneoResponse {
    private Integer idTorneo;
    String nomeTorneo;
    Set<SquadraResponse> squadre;

    public TorneoResponse(Integer idTorneo, String nomeTorneo, Set<SquadraResponse> squadre) {
        this.idTorneo = idTorneo;
        this.nomeTorneo = nomeTorneo;
        this.squadre = squadre;
    }

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

    public Set<SquadraResponse> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraResponse> squadre) {
        this.squadre = squadre;
    }
}
