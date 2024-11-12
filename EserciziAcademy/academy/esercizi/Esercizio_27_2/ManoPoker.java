package academy.esercizi.Esercizio_27_2;

public class ManoPoker {
    private final Carta[] mano;
    private static final int NUMERO_CARTE_MANO = 5;

    public ManoPoker(Carta[] mano) {
        this.mano = mano.clone();
    }

    public String verificaMano() {
        ordinaCarte();

        // Controlliamo quante volte un valore o un seme compaiono
        int[] quanteVolteValoreCompare = new int[14];  // 13 valori + 1 per l'indice 0 non utilizzato
        int[] quanteVolteSemeCompare = new int[4];     // 4 semi

        for (Carta carta : mano) {
            quanteVolteValoreCompare[carta.getValore().getValoreNumerico()]++;
            quanteVolteSemeCompare[carta.getSeme().ordinal()]++;
        }

        boolean IsStessoColore = false;
        boolean IsScala = false;

        // Controlla se è una Scala (con valori consecutivi)
        IsScala = isScala();

        // Controlla se è un Colore (tutti lo stesso seme)
        for (int i = 0; i < 4; i++) {
            if (quanteVolteSemeCompare[i] == NUMERO_CARTE_MANO) {
                IsStessoColore = true;
                break;
            }
        }


        if (IsStessoColore && IsScala && mano[0].getValore().getValoreNumerico() == 10) {
            return "Scala Reale";
        } else if (IsStessoColore && IsScala) {
            return "Scala Colore";
        } else if (haTris(quanteVolteValoreCompare) && haCoppia(quanteVolteValoreCompare)) {
            return "Full";
        } else if (IsStessoColore) {
            return "Colore";
        } else if (IsScala) {
            return "Scala";
        } else if (haTris(quanteVolteValoreCompare)) {
            return "Tris";
        } else if (haDoppiaCoppia(quanteVolteValoreCompare)) {
            return "Doppia Coppia";
        } else if (haCoppia(quanteVolteValoreCompare)) {
            return "Coppia";
        } else {
            return "Niente";
        }

    }


    private boolean isScala() {
        ordinaCarte();
        for (int i = 0; i < NUMERO_CARTE_MANO - 1; i++) {
            if (mano[i].getValore().getValoreNumerico() != mano[i + 1].getValore().getValoreNumerico() - 1) {
                return false;
            }
        }
        return true;
    }


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


    private boolean haTris(int[] valoreFrequenza) {
        for (int i = 1; i <= 13; i++) {
            if (valoreFrequenza[i] == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean haCoppia(int[] valoreFrequenza) {
        for (int i = 1; i <= 13; i++) {
            if (valoreFrequenza[i] == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean haDoppiaCoppia(int[] valoreFrequenza) {
        int coppie = 0;
        for (int i = 1; i <= 13; i++) {
            if (valoreFrequenza[i] == 2) {
                coppie++;
            }
        }
        return coppie == 2;
    }

}
