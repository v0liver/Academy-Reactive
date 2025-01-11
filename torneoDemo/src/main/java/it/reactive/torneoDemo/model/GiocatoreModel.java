package it.reactive.torneoDemo.model;

import it.reactive.torneoDemo.resource.TrasferimentiResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "GiocatoreModel.findByIdSquadra",
                    query="Select g From GiocatoreModel g where g.squadraModel.idSquadra=:idSquadra"
        )
})
@Table(name="giocatore")
public class GiocatoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idGiocatore;
    @Column(name = "nome_cognome")
    private String nomeCognome;
    @Column(name = "numero_ammonizioni")
    private Integer numeroAmmonizioni = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_squadra")
    private SquadraModel squadraModel;

    public Integer getIdGiocatore() {
        return idGiocatore;
    }

    public void setIdGiocatore(Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public Integer getNumeroAmmonizioni() {
        return numeroAmmonizioni;
    }

    public void setNumeroAmmonizioni(Integer numeroAmmonizioni) {
        this.numeroAmmonizioni = numeroAmmonizioni;
    }

    public SquadraModel getSquadraModel() {
        return squadraModel;
    }

    public void setSquadraModel(SquadraModel squadraModel) {
        this.squadraModel = squadraModel;
    }
}
