package academy.esercizi.Esercizio_27_2;

public class ManoPoker {
    private final Carta[] mano;
    private static final int NUMERO_CARTE_MANO = 5;
    private final int MAX_VALORI_POSSIBILI = 14;
    private final int MAX_SEMI_POSSIBILI = 4;


    public ManoPoker(Carta[] mano) {
        this.mano = mano;
    }

    public String verificaMano() {
        ordinaCarte();


        // Controlliamo quante volte un valore o un seme compaiono
        int[] quanteVolteValoreCompare = new int[MAX_VALORI_POSSIBILI];  // 13 valori + 1 per l'indice 0 non utilizzato
        int[] quanteVolteSemeCompare = new int[MAX_SEMI_POSSIBILI];

        for (Carta carta : mano) {
            quanteVolteValoreCompare[carta.getValore().getValoreNumerico()]++;
            quanteVolteSemeCompare[carta.getSeme().ordinal()]++;
        }

        boolean isStessoColore = false;
        boolean isScala;

        // Controlla se è una Scala (con valori consecutivi)
        isScala = isScala();

        // Controlla se è un Colore (tutti lo stesso seme)
        for (int semeIndividuato : quanteVolteSemeCompare) {
            if (semeIndividuato == NUMERO_CARTE_MANO) {
                isStessoColore = true;
                break;
            }
        }


        if (isStessoColore && isScala && mano[0].getValore().getValoreNumerico() == 10) {
            return "Scala Reale";
        } else if (isStessoColore && isScala) {
            return "Scala Colore";

        } else if (isPoker(quanteVolteValoreCompare)) {
            return "Poker";
        } else if (isTris(quanteVolteValoreCompare) && isCoppia(quanteVolteValoreCompare)) {
            return "Full";
        } else if (isStessoColore) {
            return "Colore";
        } else if (isScala) {
            return "Scala";
        } else if (isTris(quanteVolteValoreCompare)) {
            return "Tris";
        } else if (isDoppiaCoppia(quanteVolteValoreCompare)) {
            return "Doppia Coppia";
        } else if (isCoppia(quanteVolteValoreCompare)) {
            return "Coppia";
        } else {
            return "Niente";
        }

    }


    private boolean isScala() {
        for (int i = 0; i < NUMERO_CARTE_MANO - 1; i++) {
            int valoreNumericoPrimaCarta = mano[i].getValore().getValoreNumerico();
            int valoreNumericoSecondaCarta = mano[i + 1].getValore().getValoreNumerico();
            int valoreUltimaCarta = mano[NUMERO_CARTE_MANO - 1].getValore().getValoreNumerico();
            if (valoreUltimaCarta == 1) {
                valoreUltimaCarta = 14;
            }
            if (i == NUMERO_CARTE_MANO - 2) {
                return valoreNumericoPrimaCarta == valoreUltimaCarta - 1;
            }

            if (valoreNumericoPrimaCarta != valoreNumericoSecondaCarta - 1) {
                return false;
            }
        }
        return true;
    }


    private void ordinaCarte() {
        for (int i = 0; i < NUMERO_CARTE_MANO - 1; i++) {
            for (int j = i + 1; j < NUMERO_CARTE_MANO; j++) {
                int valoreNumericoPrimaCarta = mano[i].getValore().getValoreNumerico();
                int valoreNumericoSecondaCarta = mano[j].getValore().getValoreNumerico();
                if (valoreNumericoPrimaCarta > valoreNumericoSecondaCarta) {
                    Carta temp = mano[i];
                    mano[i] = mano[j];
                    mano[j] = temp;
                }
            }
        }
        if (mano[0].getValore().getValoreNumerico() == 1 && mano[1].getValore().getValoreNumerico() != 2) {
            Carta tmp = mano[0];
            for (int i = 1; i < mano.length; i++) {
                mano[i - 1] = mano[i];
            }
            mano[mano.length - 1] = tmp;

        }
        System.out.println();
        System.out.println("Le tue carte dopo l'ordinamento: ");
        for (Carta carta : mano) {
            System.out.println(carta);
        }
    }


    private boolean isPoker(int[] valoreFrequenza) {
        for (int i = 1; i < MAX_VALORI_POSSIBILI; i++) {
            if (valoreFrequenza[i] == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isTris(int[] valoreFrequenza) {
        for (int i = 1; i < MAX_VALORI_POSSIBILI; i++) {
            if (valoreFrequenza[i] == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isCoppia(int[] valoreFrequenza) {
        for (int i = 1; i < MAX_VALORI_POSSIBILI; i++) {
            if (valoreFrequenza[i] == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean isDoppiaCoppia(int[] valoreFrequenza) {
        int coppie = 0;
        for (int i = 1; i < MAX_VALORI_POSSIBILI; i++) {
            if (valoreFrequenza[i] == 2) {
                coppie++;
            }
        }
        return coppie == 2;
    }

}
