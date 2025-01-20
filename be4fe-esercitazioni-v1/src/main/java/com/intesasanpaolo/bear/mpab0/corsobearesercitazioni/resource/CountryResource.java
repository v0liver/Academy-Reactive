package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource;

import java.time.Instant;

public class CountryResource {
    String chiave;
    Instant OraAggiornamento;
    String lingua;

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public Instant getOraAggiornamento() {
        return OraAggiornamento;
    }

    public void setOraAggiornamento(Instant oraAggiornamento) {
        OraAggiornamento = oraAggiornamento;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
}
