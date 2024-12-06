package it.reactive.torneoDemo.resurce;

public class Tifoseria {
    Integer idTifoseria;

    public Tifoseria(Integer idTifoseria, String nomeTifoseria, Squadra squadra) {
        this.idTifoseria = idTifoseria;
        this.nomeTifoseria = nomeTifoseria;
        this.squadra = squadra;
    }

    String nomeTifoseria;

    public Tifoseria() {
    }

    Squadra squadra;// ---> LASCIARLO NEL MODEL

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

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }
}
