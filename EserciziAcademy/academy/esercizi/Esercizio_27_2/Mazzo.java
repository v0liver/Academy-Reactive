package academy.esercizi.Esercizio_27_2;

import java.util.Random;

public class Mazzo {
    private static final int NUMERO_CARTE = 52;
    private final Carta[] carte;
    private int indiceProssimaCartaDaEstrarre;

    public Mazzo() {
        carte = new Carta[NUMERO_CARTE];
        int i = 0;

        // Creazione delle carte del mazzo
        for (Seme seme : Seme.values()) {
            for (Valore valore : Valore.values()) {
                carte[i] = new Carta(valore, seme);
                i++;
            }
        }
        indiceProssimaCartaDaEstrarre = 0;
        mescolaMazzo();
    }


    private void mescolaMazzo() {
        Random rand = new Random();
        for (int i = 0; i < NUMERO_CARTE; i++) {
            int j = rand.nextInt(NUMERO_CARTE);
            Carta temp = carte[i];
            carte[i] = carte[j];
            carte[j] = temp;
        }
    }


    public Carta pescaUnaCarta() {
        if (indiceProssimaCartaDaEstrarre >= NUMERO_CARTE) {
            return null; // Se il mazzo è vuoto
        }
        return carte[indiceProssimaCartaDaEstrarre];
    }

}
