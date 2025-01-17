package it.reactive.demoTorneoSpringBatch.model;


import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "TifoseriaModel.findByIdSquadra",
                query = "Select t From TifoseriaModel t where t.squadraModel.idSquadra=:idSquadra"
        )
})
@Table(name = "tifoseria")
public class TifoseriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idTifoseria;
    @Column(name = "nome_tifoseria")
    private String nomeTifoseria;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_squadra")
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
