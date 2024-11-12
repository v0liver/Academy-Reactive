package academy.esercizi.Esercizio_27_2;

import java.util.Scanner;

public class TestVideoPoker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mazzo mazzo = new Mazzo();

        // Distribuiamo le prime 5 carte
        Carta[] carteGiocatore = new Carta[5];
        for (int i = 0; i < 5; i++) {
            carteGiocatore[i] = mazzo.pescaUnaCarta();
        }

        System.out.println("Le tue carte: ");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + carteGiocatore[i]);
        }


        System.out.print("Scegli le carte da scartare separate da uno spazio : ");
        String input = scanner.nextLine();
        String[] carteDaScartare = input.split(" ");

        for (String s : carteDaScartare) {
            int index = Integer.parseInt(s) - 1;
            carteGiocatore[index] = mazzo.pescaUnaCarta();
        }


        System.out.println("Le tue carte dopo lo scarto: ");
        for (Carta carta : carteGiocatore) {
            System.out.println(carta);
        }


        ManoPoker manoPoker = new ManoPoker(carteGiocatore);
        String risultato = manoPoker.verificaMano();
        System.out.println("Hai ottenuto: " + risultato);
    }
}