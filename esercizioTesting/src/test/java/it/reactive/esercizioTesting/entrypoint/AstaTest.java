package it.reactive.esercizioTesting.entrypoint;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.reactive.esercizioTesting.businesslogic.ServiceAsta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AstaTest {

    @InjectMocks
    Asta asta;
    @Mock
    ServiceAsta serviceAsta;

//    @Test
//    public void test() {
//        fail("Not yet implemented");
//    }

    private List<String> partecipanti;

    @Before
    public void setUp() {
        //partecipanti = Arrays.asList("Vito", "Giuseppe", "Laura");
        partecipanti = new ArrayList<>();
       // when(serviceAsta.getPartecipanti()).thenReturn(partecipanti);
    }

    @Test
    public void testAvvia() {

        String oggettoBandito = "Quadro";

        asta.avvia(oggettoBandito);

        verify(serviceAsta).inizializza(partecipanti);
        verify(serviceAsta).setOggettoBandito(oggettoBandito);
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


}
