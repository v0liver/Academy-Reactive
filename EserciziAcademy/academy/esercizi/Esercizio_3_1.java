package academy.esercizi;

public class Esercizio_3_1 {
    public static void main(String[] args) {
        int[][] tombola = new int[9][10];
        int contatore = 1;
        int x = 8;
        int y = 6;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                tombola[i][j] = contatore++;
                System.out.printf("%2d ", tombola[i][j]);

            }
            System.out.println();

        }
        System.out.println();
        System.out.println("Le coordinate inserite portano al numero:  " + (tombola[x - 1][y - 1]));
        stampaElementi(x, y, tombola);
    }

    private static void stampaElementi(int x, int y, int[][] tombola) {
        //int sinistra = tombola[x - 1][y - 1] - 1;//inserisco il -1 alla fine in quanto gli indici delle matrici cominciano da 0 e non da 1
        int sinistra = tombola[x-1][y-2] ;
        int destra = tombola[x-1][y];
        int sopra = tombola[x - 2][y - 1];
        int sotto = tombola[x][y - 1];
        System.out.printf("Sinistra: %d%nDestra: %d%nSopra: %d%nSotto: %d%n", sinistra, destra, sopra, sotto);

    }
}
