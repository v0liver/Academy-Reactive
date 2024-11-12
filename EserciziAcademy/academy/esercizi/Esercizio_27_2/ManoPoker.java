package academy.esercizi.Esercizio_27_2;

import academy.esercizi.Esercizio_27_2.Carta;

public class ManoPoker {
    private final Carta[] mano;
    private static final int NUMERO_CARTE_MANO = 5;

    public ManoPoker(Carta[] mano) {
        this.mano = mano.clone();
    }

    public String verificaMano() {
        ordinaCarte();

        // Controlliamo la frequenza di ogni valore e seme
        int[] valoreFrequenza = new int[14];  // 13 valori + 1 per l'indice 0 non utilizzato
        int[] semeFrequenza = new int[4];     // 4 semi

        for (Carta carta : mano) {
            valoreFrequenza[carta.getValore().getValoreNumerico()]++;
            semeFrequenza[carta.getSeme().ordinal()]++;
        }

        boolean isFlush = false;
        boolean isStraight = false;

        // Controlla se è una Scala (con valori consecutivi)
        isStraight = isScala();

        // Controlla se è un Colore (tutti lo stesso seme)
        for (int i = 0; i < 4; i++) {
            if (semeFrequenza[i] == NUMERO_CARTE_MANO) {
                isFlush = true;
                break;
            }
        }

        // Determiniamo la mano migliore
        if (isFlush && isStraight && mano[0].getValore().getValoreNumerico() == 10) {
            return "Scala Reale";
        } else if (isFlush && isStraight) {
            return "Scala Colore";
        } else if (valoreFrequenza[4] == 1) {
            return "Poker";
        } else if (valoreFrequenza[3] == 1 && valoreFrequenza[2] == 1) {
            return "Full";
        } else if (isFlush) {
            return "Colore";
        } else if (isStraight) {
            return "Scala";
        } else if (valoreFrequenza[3] == 1) {
            return "Tris";
        } else if (valoreFrequenza[2] == 2) {
            return "Doppia Coppia";
        } else if (valoreFrequenza[2] == 1) {
            return "Coppia";
        } else {
            return "Niente";
        }
    }

    // Verifica se la mano è una Scala
    private boolean isScala() {
        ordinaCarte();
        for (int i = 0; i < NUMERO_CARTE_MANO - 1; i++) {
            if (mano[i].getValore().getValoreNumerico() != mano[i + 1].getValore().getValoreNumerico() - 1) {
                return false;
            }
        }
        return true;
    }

    // Ordina le carte per valore numerico
    private void ordinaCarte() {
        for (int i = 0; i < NUMERO_CARTE_MANO - 1; i++) {
            for (int j = i + 1; j < NUMERO_CARTE_MANO; j++) {
                if (mano[i].getValore().getValoreNumerico() > mano[j].getValore().getValoreNumerico()) {
                    Carta temp = mano[i];
                    mano[i] = mano[j];
                    mano[j] = temp;
                }
            }
        }
    }
}
