package academy.esercizi;

import java.util.Scanner;

public class Esercizio_9_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci la nazionalita (Italiana/Americana): ");

        String nazionalita = scanner.nextLine();
        System.out.print("Inserisci RAL: ");
        int ral = scanner.nextInt();

        if (nazionalita.equalsIgnoreCase("italiana")) {
            double tasse = calcolaTasseItaliane(ral);
            double redditoNetto = ral - tasse;

            System.out.printf("Tasse da pagare: %.2f €%n", tasse);
            System.out.printf("Reddito netto: %.2f €%n", redditoNetto);
        } else if (nazionalita.equalsIgnoreCase("americana")) {

            System.out.print("Inserisci lo stato civile (coniugato/non coniugato): ");
            scanner.nextLine();
            String statoCivile = scanner.nextLine();
            double tasse = calcolaTasseAmericane(ral, statoCivile);
            double redditoNetto = ral - tasse;
            System.out.printf("Tasse da pagare: %.2f €%n", tasse);
            System.out.printf("Reddito netto: %.2f €%n", redditoNetto);

        } else {
            System.out.println("Nazionalita non valida.");
        }

    }

    public static double calcolaTasseAmericane(int ral, String statoCivile) {
        double tasse = 0;

        if (statoCivile.equalsIgnoreCase("non coniugato")) {
            if (ral > 0 && ral <= 8000) {
                tasse = ral * 0.10;
            } else if (ral <= 32000) {
                tasse = 800 + (ral - 8000) * 0.15;
            } else {
                tasse = 4400 + (ral - 32000) * 0.25;
            }
        } else if (statoCivile.equalsIgnoreCase("coniugato")) {
            if (ral > 0 && ral <= 16000) {
                tasse = ral * 0.10;
            } else if (ral <= 64000) {
                tasse = 1600 + (ral - 16000) * 0.15;
            } else {
                tasse = 8800 + (ral - 64000) * 0.25;
            }
        }

        return tasse;
    }


    public static double calcolaTasseItaliane(double reddito) {
        double tasse;

        if (reddito <= 15000) {
            tasse = reddito * 0.23;
        } else if (reddito <= 28000) {
            tasse = 15000 * 0.23 + (reddito - 15000) * 0.25;
        } else if (reddito <= 50000) {
            tasse = 15000 * 0.23 + (28000 - 15000) * 0.25 + (reddito - 28000) * 0.35;
        } else {
            tasse = 15000 * 0.23 + (28000 - 15000) * 0.25 + (50000 - 28000) * 0.35 + (reddito - 50000) * 0.43;
        }

        return tasse;
    }
}