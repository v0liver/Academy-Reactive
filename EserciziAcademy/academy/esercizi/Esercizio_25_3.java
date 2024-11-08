package academy.esercizi;

public class Esercizio_25_3 {


    public static void main(String[] args) {
        Esercizio_25_3 test = new Esercizio_25_3();
        test.risolvi();
    }

    private void risolvi() {
        final int DIMENSIONE_RING = 4;
        int[] combinazioneAttuale = new int[DIMENSIONE_RING];
        int[] combinazioneCorretta = new int[DIMENSIONE_RING];

        combinazioneAttuale = new int[]{9, 2, 3, 4};
        combinazioneCorretta = new int[]{1, 1, 4, 5};

        for (int i = 0; i < combinazioneAttuale.length; i++) {
            System.out.println("Il ring in posizione " + (i + 1) + " deve essere ruotato di " + movimentoRings(combinazioneAttuale[i], combinazioneCorretta[i]));
        }


    }

    private StringBuilder movimentoRings(int valoreAttuale, int valoreCorretto) {
        int differenza = valoreCorretto - valoreAttuale;
        StringBuilder giri = new StringBuilder();
        if (differenza == 0) {
            return giri.append("0 giri");
        } else if (differenza > 0) {
            if (differenza <= 5) {
                giri.append(differenza).append(" ").append("rotazioni in alto");
            } else {
                giri.append(10 - differenza).append(" ").append("rotazioni in basso");
            }
            return giri;
        } else {
            if (Math.abs(differenza) <= 5) {
                giri.append(Math.abs(differenza)).append(" ").append("rotazioni in alto");
            } else {
                giri.append(10 - Math.abs(differenza)).append(" ").append("rotazioni in basso");
            }
            return giri;
        }
    }
}
