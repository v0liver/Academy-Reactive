package it.reactive.demoTorneoSpringBatch.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "torneo")
public class TorneoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idTorneo;
    @Column(name = "nome_torneo")
    private String nomeTorneo;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tornei")
    private List<SquadraModel> squadre;

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

    public List<SquadraModel> getSquadre() {
        return squadre;
    }

    public void setSquadre(List<SquadraModel> squadre) {
        this.squadre = squadre;
    }
}
