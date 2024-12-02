package it.reactive.esercizioTesting.businesslogic;

import it.reactive.esercizioTesting.entrypoint.Asta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceAstaTest {
    static ServiceAsta serviceAsta;
    static Asta asta;
    List<String> partecipanti = new ArrayList<>(Arrays.asList("Vito","Giuseppe","Laura"));


    @Before
    public void before() {
        serviceAsta = new ServiceAsta();
        asta = new Asta(serviceAsta);
    }


        @Test
    public void inizializza() {
        assertEquals(, serviceAsta.getPartecipanti());
        assertTrue(sessioneAsta.isInCorso());
        assertEquals(new ArrayList<String>(),sessioneAsta.getPartecipanti());

    }
    @Test
    public void testRilanciaValorePositivo() {
        serviceAsta.rilancia("Partecipante1", 10);
        assertEquals(10, serviceAsta.getValoreSessioneAsta());
        assertEquals("Partecipante1", serviceAsta.getVincitore());
    }


    @Test
    public void setOggettoBandito() {
    }

    @Test
    public void rilancia() {
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