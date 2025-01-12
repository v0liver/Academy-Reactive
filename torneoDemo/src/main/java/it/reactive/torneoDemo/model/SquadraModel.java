package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "SquadraModel.findById",
                query = "Select s From SquadraModel s where s.idSquadra=:id"
        ),
        @NamedQuery(
                name = "SquadraModel.findByNome",
                query = "Select s From SquadraModel s where s.nome = :nomeSquadra"
        )
})
@Table(name = "squadra")
public class SquadraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idSquadra;
    @Column(name = "nome", unique = true)
    private String nome;
    @Column(name = "colori_sociali")
    private String coloriSociali;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "squadraModel")
    private Set<GiocatoreModel> giocatori;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "squadraModel")
    private TifoseriaModel tifoseria;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "squadra_torneo",
            joinColumns = @JoinColumn(name = "id_squadra"),
            inverseJoinColumns = @JoinColumn(name = "id_torneo")
    )
    private Set<TorneoModel> tornei;

    public TifoseriaModel getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaModel tifoseria) {
        this.tifoseria = tifoseria;
    }


    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public Set<GiocatoreModel> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreModel> giocatori) {
        this.giocatori = giocatori;
    }


    public Set<TorneoModel> getTornei() {
        return tornei;
    }

    public void setTornei(Set<TorneoModel> tornei) {
        this.tornei = tornei;
    }

}
