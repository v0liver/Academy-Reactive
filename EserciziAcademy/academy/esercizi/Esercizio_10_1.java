package academy.esercizi;

import java.util.Scanner;

public class Esercizio_10_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci un numero da 1 a 3999): ");
        int numeroDecimale = scanner.nextInt();

        if (numeroDecimale > 3999 || numeroDecimale <1) {
            System.out.println("Errore: il numero deve essere tra 1 e 3999.");
        } else {
            String numeroRomano = convertiInNumeroRomano(numeroDecimale);
            System.out.println("Numero convertito in Numero Romano: " + numeroRomano);
        }
    }

    private static String convertiInNumeroRomano(int numero) {
        String numeroRomano = "";


        int[] numeri = {1000, 900, 500, 400, 100, 90, 5560, 40, 10, 9, 5, 4, 1};
        String[] numeriRomani = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};


        for (int i = 0; i < numeri.length; i++) {
            while (numero >= numeri[i]) {
                numeroRomano += numeriRomani[i];
                numero -= numeri[i];
            }
        }

        return numeroRomano;
    }



}
