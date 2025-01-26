package it.reactive.torneoDemoMongo.resource;

import java.util.List;

public class TorneoResource {
    private String idTorneo;
    String nomeTorneo;
    List<String> squadre;


    public String getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(String idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public List<String> getSquadre() {
        return squadre;
    }

    public void setSquadre(List<String> squadre) {
        this.squadre = squadre;
    }
}
