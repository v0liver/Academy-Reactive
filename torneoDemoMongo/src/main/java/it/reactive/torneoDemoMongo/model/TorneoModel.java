package it.reactive.torneoDemoMongo.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "tornei")
public class TorneoModel {

    @Id
    private ObjectId _id;

    private String nomeTorneo;

    private List<String> squadre;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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
