package it.reactive.torneoDemo.resurce;

import java.util.Set;

public class Torneo {
    Integer idTorneo;
    String nomeTorneo;
    Set<Squadra> squadre;

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public Torneo() {
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public Set<Squadra> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<Squadra> squadre) {
        this.squadre = squadre;
    }

    public Torneo(Integer idTorneo, String nomeTorneo, Set<Squadra> squadre) {
        this.idTorneo = idTorneo;
        this.nomeTorneo = nomeTorneo;
        this.squadre = squadre;
    }
}
