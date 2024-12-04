package it.reactive.esercizioTesting.entrypoint;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.reactive.esercizioTesting.businesslogic.ServiceAsta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class AstaTest {

    @InjectMocks
    Asta asta;
    @Mock
    ServiceAsta serviceAsta;


    private List<String> partecipanti;

    @Before
    public void setUp() {

        //partecipanti = Arrays.asList("Vito", "Giuseppe", "Laura");
        //partecipanti = new ArrayList<>();
        // when(serviceAsta.getPartecipanti()).thenReturn(partecipanti);
    }

    @Test
    public void testAvvia() {
        String oggettoBandito = "Quadro";
        asta.avvia(oggettoBandito);
        //ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(serviceAsta).inizializza(anyList());
        verify(serviceAsta).setOggettoBandito(any());
    }

    @Test
    public void testAvviaAstaFinita() {
        String oggettoBandito = "Quadro";
        when(serviceAsta.verificaFineAsta()).thenReturn(true);
        asta.avvia(oggettoBandito);
        verify(serviceAsta).fine();
    }

    @Test
    public void testRilancia() {
        String nomeBanditore = "Giuseppe";
        int valore = 100;


        when(serviceAsta.getValoreSessioneAsta()).thenReturn(200);

        int valoreCorrente = asta.rilancia(nomeBanditore, valore);

        verify(serviceAsta).rilancia(nomeBanditore, valore);
        assertEquals(200, valoreCorrente);
    }

    @Test
    public void testPassa() {
        String nomeBanditore = "Vito";

        when(serviceAsta.verificaFineAsta()).thenReturn(true);

        boolean fineAsta = asta.passa(nomeBanditore);

        verify(serviceAsta).rilancia(nomeBanditore, -1);
        assertTrue(fineAsta);
    }

    @Test
    public void verificaFineAsta() {
        when(serviceAsta.verificaFineAsta()).thenReturn(false);
        asta.verificaFineAsta();
        verify(serviceAsta).verificaFineAsta();
    }

    @Test
    public void addPartecipante() {
        //  when(serviceAsta.getPartecipanti()).thenReturn(new ArrayList<>(Arrays.asList("Default")));
        doReturn(new ArrayList<>(Arrays.asList("Default"))).when(serviceAsta).getPartecipanti();
        asta.addPartecipante("Default");
        verify(serviceAsta, times(2)).getPartecipanti();
        // verify(serviceAsta).addPartecipante(any());
    }

    @Test
    public void addPartecipanteSenzaIf() {
        //  when(serviceAsta.getPartecipanti()).thenReturn(new ArrayList<>(Arrays.asList("Default")));
        doReturn(new ArrayList<>(Arrays.asList("Default", "Ciao"))).when(serviceAsta).getPartecipanti();
        asta.addPartecipante("Ferdinando");
        verify(serviceAsta, times(2)).getPartecipanti();
        // verify(serviceAsta).addPartecipante(any());
    }

    @Test
    public void addPartecipanteSenzaIf3() {
        //  when(serviceAsta.getPartecipanti()).thenReturn(new ArrayList<>(Arrays.asList("Default")));
        doReturn(new ArrayList<>(Arrays.asList("Ferdinando"))).when(serviceAsta).getPartecipanti();
        asta.addPartecipante("Ferdinando");
        verify(serviceAsta, times(2)).getPartecipanti();
        // verify(serviceAsta).addPartecipante(any());
    }


    @Test
    public void addPartecipanteSenzaIf2() {
        //  when(serviceAsta.getPartecipanti()).thenReturn(new ArrayList<>(Arrays.asList("Default")));
        doReturn(new ArrayList<>(Arrays.asList("Topo", "Ciao"))).when(serviceAsta).getPartecipanti();
        asta.addPartecipante("Ferdinando");
        asta.addPartecipante("Ciao");
        verify(serviceAsta, times(3)).getPartecipanti();
        // verify(serviceAsta).addPartecipante(any());
    }

    @Test
    public void visualizzaPartecipantiSessioneCorrente() {
        when(serviceAsta.getPartecipanti()).thenReturn(new ArrayList<>(Arrays.asList("Default")));
        asta.visualizzaPartecipantiSessioneCorrente();
        verify(serviceAsta, times(2)).getPartecipanti();

    }

    @Test
    public void fineAstaForzata() {
        asta.fineAstaForzata();
        verify(serviceAsta).fine();
        verify(serviceAsta).getValoreSessioneAsta();
        verify(serviceAsta).getVincitore();
        verify(serviceAsta).verificaFineAsta();

    }

    @Test
    public void getValoreCorrente() {
        asta.getValoreCorrente();
        verify(serviceAsta).getValoreSessioneAsta();
    }

    @Test
    public void setPartecipanti() {
        List<String> tmp = new ArrayList<>(Arrays.asList("Ciao", "Pippo"));
        asta.setPartecipanti(tmp);
        verify(serviceAsta).setPartecipanti(any());
    }


}
