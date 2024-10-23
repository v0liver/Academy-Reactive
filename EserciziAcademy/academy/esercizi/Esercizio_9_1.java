package academy.esercizi;

import java.math.BigDecimal;
import java.util.Scanner;

public class Esercizio_9_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci la nazionalita (Italiana/Americana): ");

        String nazionalita = scanner.nextLine();
        System.out.print("Inserisci RAL: ");
        int inputRal = scanner.nextInt();
        BigDecimal ral = new BigDecimal(inputRal);
        if (nazionalita.equalsIgnoreCase("italiana")) {

            BigDecimal tasse = calcolaTasseItaliane(ral);
            BigDecimal redditoNetto = ral.subtract(tasse);

            System.out.printf("Tasse da pagare: %.2f €%n", tasse);
            System.out.printf("Reddito netto: %.2f €%n", redditoNetto);
        } else if (nazionalita.equalsIgnoreCase("americana")) {

            System.out.print("Inserisci lo stato civile (coniugato/non coniugato): ");
            scanner.nextLine();
            String statoCivile = scanner.nextLine();
            double tasse = calcolaTasseAmericane(inputRal, statoCivile);
            double redditoNetto = inputRal - tasse;
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


    public static BigDecimal calcolaTasseItaliane(BigDecimal reddito) {
        BigDecimal tasse;
        BigDecimal soglia15k = new BigDecimal(15000);
        BigDecimal soglia28k = new BigDecimal(28000);
        BigDecimal soglia50k = new BigDecimal(50000);
        BigDecimal tassazione23 = new BigDecimal("0.23");
        BigDecimal tassazione25 = new BigDecimal("0.25");
        BigDecimal tassazione35 = new BigDecimal("0.35");
        BigDecimal tassazione43 = new BigDecimal("0.43");
        if (reddito.compareTo(soglia15k) <= 0) {
            tasse = reddito.multiply(tassazione23);
        } else if (reddito.compareTo(soglia28k) <= 0) {
            tasse = soglia15k.multiply(tassazione23).add(reddito.subtract(soglia15k).multiply(tassazione25));
        } else if (reddito.compareTo(soglia50k) <= 0) {
            // tasse = 15000 * 0.23 + (28000 - 15000) * 0.25 + (reddito - 28000) * 0.35;

            tasse = soglia15k.multiply(tassazione23).add(soglia28k.subtract(soglia15k).multiply(tassazione25).add(reddito.subtract(soglia28k).multiply(tassazione35)));
        } else {
            // tasse = 15000 * 0.23 + (28000 - 15000) * 0.25 + (50000 - 28000) * 0.35 + (reddito - 50000) * 0.43;
            tasse = soglia15k.multiply(tassazione23).add(soglia28k.subtract(soglia15k).multiply(tassazione25).add(reddito.subtract(soglia28k).multiply(tassazione35))).add(reddito.subtract(soglia50k).multiply(tassazione43));
        }

        return tasse;
    }
}