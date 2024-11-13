package academy.esercizi;

import java.util.Scanner;

public class Esercizio_28 {
    static Scanner scanner = new Scanner(System.in);
    final static int RIGHE = 8;
    final static int COLONNE = 9;

    public static void main(String[] args) {
        Esercizio_28 esercizio281 = new Esercizio_28();
        esercizio281.play();
    }

    private void play() {
        char[][] scacchiera = new char[RIGHE][COLONNE];
        scacchieraIniziale(scacchiera);

        int i = 0;
        while (true) {
            System.out.println();
            System.out.println("Generazione n°" + (i + 1));
            generazione(scacchiera);
            stampa(scacchiera);
            i++;
            System.out.println("Premi invio per continuare (oppure 'q' per uscire)");
            String comando = scanner.nextLine();
            if (comando.equals("q")) {
                break;
            }
        }
    }

    private static void generazione(char[][] arr) {
        char[][] nuovaGenerazione = new char[RIGHE][COLONNE];

        // Calcolare la nuova generazione
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                int adiacenti = quantiAdiacenti(arr, i, j);

                if (arr[i][j] == 'o') {
                    if (adiacenti < 2 || adiacenti > 3) {
                        nuovaGenerazione[i][j] = ' '; // Morte per solitudine o sovraffollamento
                    } else {
                        nuovaGenerazione[i][j] = 'o'; // Cella rimane occupata
                    }
                } else { // Cella con spazio
                    if (adiacenti == 3) {
                        nuovaGenerazione[i][j] = 'o'; // Nascita
                    } else {
                        nuovaGenerazione[i][j] = ' '; // Rimane vuota
                    }
                }
            }
        }

        // Aggiornare la scacchiera con la nuova generazione
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                arr[i][j] = nuovaGenerazione[i][j];
            }
        }
    }

    private static int quantiAdiacenti(char[][] arr, int riga, int colonna) {
        int adiacenti = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) continue;

                int nuovaRiga = riga + i;
                int nuovaColonna = colonna + j;


                if (nuovaRiga >= 0 && nuovaRiga < RIGHE && nuovaColonna >= 0 && nuovaColonna < COLONNE) {
                    if (arr[nuovaRiga][nuovaColonna] == 'o') {
                        adiacenti++;
                    }
                }
            }
        }
        return adiacenti;
    }

    private static void scacchieraIniziale(char[][] scacchiera) {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                scacchiera[i][j] = ' ';
            }
        }

        System.out.println("Inserisci la scacchiera iniziale usando 'o' oppure ' ' :");
        for (int i = 0; i < RIGHE ; i++) {
            String input = scanner.nextLine();
            for (int j = 0; j < COLONNE; j++) {
                if (j  < input.length()) {
                    scacchiera[i][j] = input.charAt(j);
                }
            }
        }

        System.out.println("Scacchiera iniziale: ");
        stampa(scacchiera);
    }

    private static void stampa(char[][] scacchiera) {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                System.out.print(scacchiera[i][j]);
            }
            System.out.println();
        }
    }
}
