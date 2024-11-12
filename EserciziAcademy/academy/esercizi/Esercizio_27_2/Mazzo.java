package academy.esercizi.Esercizio_27_2;

import academy.esercizi.Esercizio_27_2.Carta;
import academy.esercizi.Esercizio_27_2.Seme;
import academy.esercizi.Esercizio_27_2.Valore;

import java.util.Random;

public class Mazzo {
    private static final int NUMERO_CARTE = 52;
    private final Carta[] carte;
    private int indiceMazzo; // Indica la prossima carta da estrarre

    public Mazzo() {
        carte = new Carta[NUMERO_CARTE];
        int i = 0;
        // Creazione delle carte del mazzo
        for (Seme seme : Seme.values()) {
            for (Valore valore : Valore.values()) {
                carte[i++] = new Carta(valore, seme);
            }
        }
        indiceMazzo = 0;
        mescola();
    }

    // Mescola il mazzo
    private void mescola() {
        Random rand = new Random();
        for (int i = 0; i < NUMERO_CARTE; i++) {
            int j = rand.nextInt(NUMERO_CARTE);
            Carta temp = carte[i];
            carte[i] = carte[j];
            carte[j] = temp;
        }
    }

    // Estrae una carta dal mazzo
    public Carta estraiCarta() {
        if (indiceMazzo >= NUMERO_CARTE) {
            return null; // Se il mazzo è vuoto
        }
        return carte[indiceMazzo++];
    }

    // Numero di carte rimanenti nel mazzo
    public int numeroCarteRimanenti() {
        return NUMERO_CARTE - indiceMazzo;
    }
}
