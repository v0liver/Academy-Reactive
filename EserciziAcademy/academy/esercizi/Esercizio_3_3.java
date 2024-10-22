package academy.esercizi;

import java.util.Random;

public class Esercizio_3_3 {
    public static void main(String[] args) {
        String[][] scacchiera = new String[3][3];
        inizializzaScacchiera(scacchiera);
        assegnaX(scacchiera);
        riempiCaselleCasualmente(scacchiera);
        stampaScacchiera(scacchiera);
        mostraDiagonale(scacchiera);
    }

    private static void inizializzaScacchiera(String[][] scacchiera) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                scacchiera[i][j] = " ";
            }
        }
    }

    private static void assegnaX(String[][] scacchiera) {
        scacchiera[0][2] = "x";
    }

    private static void riempiCaselleCasualmente(String[][] scacchiera) {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (scacchiera[i][j].equals(" ")) {
                    scacchiera[i][j] = random.nextBoolean() ? "o" : "x";
                }
            }
        }
    }

    private static void stampaScacchiera(String[][] scacchiera) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(scacchiera[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void mostraDiagonale(String[][] scacchiera) {
        System.out.print("Gli elementi sulla diagonale principale sono: ");
        for (int i = 0; i < 3; i++) {
            System.out.print(scacchiera[i][i] + " ");
        }
        System.out.println();
    }
}
