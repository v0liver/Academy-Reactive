package it.reactive.torneoDemoMongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(value = "squadre")
public class SquadraModel {
    @Id
    private ObjectId _id;

    private String nome;

    private String coloriSociali;

    private List<GiocatoreModel> giocatori = new ArrayList<>();

    public List<GiocatoreModel> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(List<GiocatoreModel> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaModel getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaModel tifoseria) {
        this.tifoseria = tifoseria;
    }

    //    private Set<GiocatoreModel> giocatori;
//
    private TifoseriaModel tifoseria;
//
//    private Set<TorneoModel> tornei;
//
//    public TifoseriaModel getTifoseria() {
//        return tifoseria;
//    }
//
//    public void setTifoseria(TifoseriaModel tifoseria) {
//        this.tifoseria = tifoseria;
//    }


    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

//    public Set<GiocatoreModel> getGiocatori() {
//        return giocatori;
//    }
//
//    public void setGiocatori(Set<GiocatoreModel> giocatori) {
//        this.giocatori = giocatori;
//    }
//
//
//    public Set<TorneoModel> getTornei() {
//        return tornei;
//    }
//
//    public void setTornei(Set<TorneoModel> tornei) {
//        this.tornei = tornei;
//    }

}
