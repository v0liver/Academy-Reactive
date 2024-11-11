package academy.esercizi;

import java.util.Scanner;

public class Esercizio_27_1 {
    public final int DIMENSIONE_MATRICE = 4;

    public static void main(String[] args) {
        Esercizio_27_1 test = new Esercizio_27_1();
        test.risolvi();
    }

    private void risolvi() {
        Scanner scan = new Scanner(System.in);
        int[][] matrice = new int[DIMENSIONE_MATRICE][DIMENSIONE_MATRICE];
        int counter = 1;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                System.out.printf("Inserisci il %d numero:", counter);
                counter++;
                matrice[i][j] = scan.nextInt();
            }
        }
        for (int[] ints : matrice) {
            for (int j = 0; j < matrice.length; j++) {
                System.out.printf("%3d ", ints[j]);
            }
            System.out.println();
        }

        if (contieneNumeriDa1a16(matrice)) {
            System.out.println("La matrice contiene tutti i numeri da 1 a 16.");
        } else {
            System.out.println("La matrice NON contiene tutti i numeri da 1 a 16.");
        }

        if (calcoloDiagonale1(matrice) == calcoloDiagonale2(matrice) && calcoloDiagonale1(matrice) == calcolaSommaColonne(matrice) && calcolaSommaColonne(matrice) == calcolaSommaRighe(matrice)) {
            System.out.println("Il quadrato è un quadrato magico.");

        } else System.out.println("Il quadrato non è un quadrato magico.");


    }

    private int calcolaSommaRighe(int[][] matrice) {
        int sommaRighe;
        int contatoreUguali = 0;
        int tmp1;
        int tmp2 = 0;

        for (int i = 0; i < matrice.length; i++) {
            sommaRighe = 0;
            for (int j = 0; j < matrice.length; j++) {
                sommaRighe += matrice[i][j];


            }
            tmp1 = sommaRighe;
            if (i > 0) {
                if (tmp2 == tmp1) {
                    contatoreUguali++;
                }
            }
            tmp2 = tmp1;


        }
        if (contatoreUguali == matrice.length - 1) {
            return tmp2;
        } else return -1;
    }

    private int calcolaSommaColonne(int[][] matrice) {
        int sommaColonne;
        int contatoreUguali = 0;
        int tmp1;
        int tmp2 = 0;

        for (int i = 0; i < matrice.length; i++) {
            sommaColonne = 0;
            for (int[] ints : matrice) {
                sommaColonne += ints[i];


            }
            tmp1 = sommaColonne;
            if (i > 0) {
                if (tmp2 == tmp1) {
                    contatoreUguali++;
                }
            }
            tmp2 = tmp1;


        }
        if (contatoreUguali == matrice.length - 1) {
            return tmp2;
        } else return -1;
    }

    private int calcoloDiagonale1(int[][] matrice) {
        int somma = 0;
        for (int i = 0; i < matrice.length; i++) {
            somma += matrice[i][i];
        }
        return somma;
    }

    private int calcoloDiagonale2(int[][] matrice) {
        int somma = 0;
        for (int i = matrice.length - 1; i >= 0; i--) {
            somma += matrice[i][i];
        }
        return somma;
    }

    public static boolean contieneNumeriDa1a16(int[][] matrice) {

        boolean[] numeriTrovati = new boolean[16];


        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                int numero = matrice[i][j];

                if (numero >= 1 && numero <= 16) {
                    numeriTrovati[numero - 1] = true;
                }
            }
        }


        for (boolean trovato : numeriTrovati) {
            if (!trovato) {
                return false;
            }
        }

        return true;
    }
}

