package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="torneo")
public class TorneoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idTorneo;
    @Column(name = "nome_torneo")
    private String nomeTorneo;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "tornei")
    private Set<SquadraModel> squadre;

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

    public Set<SquadraModel> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraModel> squadre) {
        this.squadre = squadre;
    }
}
