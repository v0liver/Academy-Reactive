package academy.esercizi.Esercizio_27_2;

import java.util.Scanner;

public class TestVideoPoker {

    public static void main(String[] args) {
        final int MAX_CARTE_IN_MANO = 5;
        Scanner scanner = new Scanner(System.in);
        Mazzo mazzo = new Mazzo();

        // Distribuiamo le prime 5 carte
        Carta[] carteGiocatore = new Carta[MAX_CARTE_IN_MANO];
        for (int i = 0; i < carteGiocatore.length; i++) {
            carteGiocatore[i] = mazzo.pescaUnaCarta();
        }


        System.out.println();
        System.out.println("Le tue carte: ");
        for (int i = 0; i < carteGiocatore.length; i++) {
            System.out.println((i + 1) + ". " + carteGiocatore[i]);
        }


        System.out.print("Scegli le carte da scartare separate da uno spazio : ");
        String input = scanner.nextLine();
        String[] carteDaScartare = input.split(" ");

        for (String s : carteDaScartare) {
            int index = Integer.parseInt(s) - 1;
            carteGiocatore[index] = mazzo.pescaUnaCarta();
        }

//        carteGiocatore[0] = new Carta( Valore.ASSO,Seme.CUORI);
//        carteGiocatore[1] = new Carta( Valore.ASSO,Seme.QUADRI);
//        carteGiocatore[2] = new Carta( Valore.ASSO,Seme.PICCHE);
//        carteGiocatore[3] = new Carta( Valore.ASSO,Seme.FIORI);
//        carteGiocatore[4] = new Carta( Valore.RE,Seme.CUORI);

        System.out.println("Le tue carte dopo lo scarto: ");
        for (Carta carta : carteGiocatore) {
            System.out.println(carta);
        }


        ManoPoker manoPoker = new ManoPoker(carteGiocatore);
        String risultato = manoPoker.verificaMano();
        System.out.println("Hai ottenuto: " + risultato);


    }
}
