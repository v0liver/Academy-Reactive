package it.reactive.esercizioTesting.businesslogic;

import it.reactive.esercizioTesting.entrypoint.Asta;
import it.reactive.esercizioTesting.exception.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceAstaTest {
    static ServiceAsta serviceAsta = new ServiceAsta();
    static Asta asta;
    List<String> partecipanti = new ArrayList<>(Arrays.asList("Vito", "Giuseppe", "Laura"));


    @Before
    public void setUp() {
        serviceAsta = new ServiceAsta();
        asta = new Asta(serviceAsta);
        serviceAsta.inizializza(partecipanti);
    }


    @Test
    public void setOggetto() {
        serviceAsta.setOggettoBandito("ciao");
        //Non posso controllare il metodo con junit perchè non esiste il getOggettoBandito
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

    @Test
    public void rilanciaValoreMaggioreZero() {
        serviceAsta.rilancia("Vito", 10);
        assertEquals("Vito", serviceAsta.getVincitore());
    }

    @Test
    public void rilanciaValoreMinoreZero() {
        serviceAsta.rilancia("Vito", -10);

        //Non posso controllare altro con junit in quanto non posso riprendere i valori di
        // sessioneAsta.getPartecipantiPassati().add(nomePartecipante);
    }

    @Test
    public void rilanciaProgressivoUgualePartecipantiSize() {
        List<String> tmp = new ArrayList<>(Arrays.asList("Vito", "Laura"));
        serviceAsta.inizializza(tmp);
        serviceAsta.rilancia("Vito", 10);
        serviceAsta.rilancia("Laura", 20);


        //Non posso controllare altro con junit in quanto non posso controllare
        // se effettivamente progressivoTurnoCorrente==0
    }

    @Test
    public void rilancia() {
        serviceAsta.rilancia("Vito", -10);
        serviceAsta.rilancia("Giuseppe", 20);
        serviceAsta.rilancia("Laura", 40);
        serviceAsta.rilancia("Giuseppe", 50);


    }

    @Test
    public void getValoreSessioneAsta() {
        assertEquals(1, serviceAsta.getValoreSessioneAsta());
    }

    @Test
    public void getVincitore() {
        //serviceAsta.getVincitore();
        assertEquals("Vito", serviceAsta.getVincitore());

    }

    @Test(expected = UnsupportedOperationException.class)
    public void setPartecipanti() {
        serviceAsta.setPartecipanti(partecipanti);
    }

    @Test(expected = AstaInCorsoException.class)
    public void addPartecipanteAstaInCorsoException() {
        serviceAsta.addPartecipante("Santina");

    }

    @Test(expected = PartecipanteEsistenteException.class)
    public void addPartecipantePartecipanteEsistenteException() {
        serviceAsta.fine();
        serviceAsta.addPartecipante("Vito");


    }

    @Test
    public void addPartecipantePartecipante() {
        serviceAsta.fine();
        serviceAsta.addPartecipante("Santina");
        assertEquals("Santina", partecipanti.get(3));


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