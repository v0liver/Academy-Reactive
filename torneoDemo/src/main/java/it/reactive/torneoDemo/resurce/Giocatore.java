package it.reactive.torneoDemo.resurce;

public class Giocatore {
    Integer idGiocatore;
    String nomeCognome;
    Integer numeroAmmonizioni;

    public Giocatore(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni, Squadra squadra) {
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.squadra = squadra;
    }

    public Giocatore() {
    }

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

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    Squadra squadra;// ---> LASCIARLO NEL MODEL
}
