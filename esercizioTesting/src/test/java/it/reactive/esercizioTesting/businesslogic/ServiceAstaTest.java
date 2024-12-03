package it.reactive.esercizioTesting.businesslogic;

import it.reactive.esercizioTesting.entrypoint.Asta;
import it.reactive.esercizioTesting.exception.AstaTerminataException;
import it.reactive.esercizioTesting.exception.PartecipanteNonCensitoException;
import it.reactive.esercizioTesting.exception.TurnoNonValidoException;
import it.reactive.esercizioTesting.exception.ValoreNonAmmessoException;
import it.reactive.esercizioTesting.oggetto.SessioneAsta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceAstaTest {
    static ServiceAsta serviceAsta=new ServiceAsta();
    static Asta asta;
    List<String> partecipanti = new ArrayList<>(Arrays.asList("Vito", "Giuseppe", "Laura"));


    @Before
    public void before() {
        serviceAsta = new ServiceAsta();
        asta = new Asta(serviceAsta);
        serviceAsta.inizializza(partecipanti);
    }


    @Test
    public void inizializza() {
        // assertEquals(partecipanti, serviceAsta.getPartecipanti());
//        assertTrue(serviceAsta.isInCorso());
//        assertEquals(new ArrayList<String>(),sessioneAsta.getPartecipanti());
    }


//    @Test(expected = AstaTerminataException.class)
//    public void rilanciaConAstaTerminataException() {
//
//    }
    @Test
    public void setOggetto(){
        serviceAsta.setOggettoBandito("ciao");
    }

    @Test(expected = PartecipanteNonCensitoException.class)
    public void rilanciaPartecipanteNonCensitoException() {
        serviceAsta.rilancia("Daniele", 5);
    }

    @Test(expected = TurnoNonValidoException.class)
    public void rilanciaTurnoNonValidoException() {
        serviceAsta.rilancia("Giuseppe", 10);
    }

    @Test(expected = ValoreNonAmmessoException.class)
    public void rilanciaValoreNonAmmessoException() {
        serviceAsta.rilancia("Vito", 0);
    }

    @Test(expected = AstaTerminataException.class)
    public void rilanciaAstaTerminataException() {
        List<String> partecipanti = new ArrayList<>();
        partecipanti.add("Daniele");
        serviceAsta.inizializza(partecipanti);
        serviceAsta.rilancia("Vito", 5);
    }

    public void rilanciaAstaTerminataException() {
//        List<String> partecipanti = new ArrayList<>();
//        partecipanti.add("Daniele");
//        serviceAsta.inizializza(partecipanti);
//        serviceAsta.rilancia("Vito", 5);
        serviceAsta.rilancia("Vito",-1);
    }

    @Test
    public void getValoreSessioneAsta() {
    }

    @Test
    public void getVincitore() {
    }

    @Test
    public void setPartecipanti() {
    }

    @Test
    public void addPartecipante() {
    }

    @Test
    public void getPartecipanti() {
    }

    @Test
    public void fine() {
    }

    @Test
    public void verificaFineAsta() {
    }
}