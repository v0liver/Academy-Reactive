package academy.esercizi;

public class Esercizio_3_1 {
    public static void main(String[] args) {
        int[][] tombola = new int[9][10];
        int x = 5;
        int y = 5;
        int numero = 45;
        popolaTombola(tombola);
        System.out.println("Le coordinate inserite portano al numero:  " + (tombola[x - 1][y - 1]));
        stampaElementiAdiacenti(x, y, tombola);


        stampaElementiAdiacentiDaNumero(numero, tombola);
    }

    private static void stampaElementiAdiacenti(int x, int y, int[][] tombola) {


        if (y > 1) {
            int sinistra = tombola[x - 1][y - 2];
            System.out.printf("Sinistra: %d%n", sinistra);
        }
        if (y < 10) {

            int destra = tombola[x - 1][y];
            System.out.printf("Destra: %d%n", destra);
        }
        if (x > 1) {
            int sopra = tombola[x - 2][y - 1];
            System.out.printf("Sopra: %d%n", sopra);
        }
        if (x < 9) {
            int sotto = tombola[x][y - 1];
            System.out.printf("Sotto: %d%n", sotto);
        }


    }

    private static void stampaElementiAdiacentiDaNumero(int numero, int[][] tombola) {

        for (int i = 0; i < tombola.length; i++) {
            for (int j = 0; j < tombola[i].length; j++) {
                if (tombola[i][j] == numero) {
                    System.out.printf("Il numero %d si trova alle coordinate (%d, %d)%n", numero, i + 1, j + 1);
                    stampaElementiAdiacenti(i + 1, j + 1, tombola);
                    return;
                }
            }
        }
        System.out.println("Numero non trovato.");
    }

    private static void popolaTombola(int[][] tombola) {
        int contatore = 1;
        for (int righe = 0; righe < 9; righe++) {
            for (int colonne = 0; colonne < 10; colonne++) {
                tombola[righe][colonne] = contatore++;
                System.out.printf("%2d ", tombola[righe][colonne]);

            }
            System.out.println();

        }

    }


}

