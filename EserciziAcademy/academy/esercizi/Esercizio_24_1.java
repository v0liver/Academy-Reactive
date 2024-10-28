package academy.esercizi;

import java.util.Random;
import java.util.Scanner;

public class Esercizio_24_1 {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);


        int biglie = random.nextInt(91) + 10;

        // Determina se l'utente o il computer gioca per primo
        boolean turnoUtente = random.nextInt(2) == 0;

        // Modalità intelligente o stupida
        boolean modalitaIntelligente = random.nextInt(2) == 1;//0 attivata, 1 disattivata

        System.out.println("---------------Gioco di Nim---------------");
        System.out.println();
        System.out.println("Il mucchio iniziale contiene " + biglie + " biglie.");
        System.out.println((turnoUtente ? "L'utente" : "Il computer") + " gioca per primo.");
        System.out.println("Il computer gioca in modalità " + (modalitaIntelligente ? "intelligente." : "stupida."));

        while (biglie > 1) {
            if (turnoUtente) {
                // Turno dell'utente
                int bigliePrese;
                do {
                    System.out.println("Nel mucchio ci sono " + biglie + " biglie.");
                    System.out.print("Quante biglie vuoi prendere? ");
                    bigliePrese = scanner.nextInt();
                } while (bigliePrese < 1 || bigliePrese > biglie / 2);

                biglie -= bigliePrese;
                System.out.println("Hai preso " + bigliePrese + " biglie. Rimangono " + biglie + " biglie.");
            } else {

                // Turno del computer
                int bigliePrese;
                if (modalitaIntelligente) {
                    bigliePrese = mossaIntelligente(biglie);
                    if (bigliePrese == -1) {
                        bigliePrese = random.nextInt(biglie / 2) + 1;
                    }
                } else {
                    bigliePrese = random.nextInt(biglie / 2) + 1;
                }

                biglie -= bigliePrese;
                System.out.println("Il computer ha preso " + bigliePrese + " biglie. Rimangono " + biglie + " biglie.");
            }

            turnoUtente = !turnoUtente; // Cambio giocatore tra un turno e l'altro
        }

        // Calcolo vincitore
        if (!turnoUtente) {// Uso "!turnoUtente" al posto di "turnoutente" perchè alla riga 56 cambio il turnoutente per switchare tra utente e computer
            System.out.println("Il computer ha preso l'ultima biglia. Hai vinto!");

        } else {
            System.out.println("Hai preso l'ultima biglia. Il computer ha vinto!");

        }

    }


    private static int mossaIntelligente(int biglie) {
        int[] potenze2MenoUno = {3, 7, 15, 31, 63};

        for (int potenza : potenze2MenoUno) {
            if (biglie > potenza) {
                return biglie - potenza;
            }
        }

        return -1; //  biglie = potenza quindi nessuna mossa intelligente da poter fare
    }


}
