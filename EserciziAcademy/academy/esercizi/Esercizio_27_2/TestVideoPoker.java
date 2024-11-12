package academy.esercizi.Esercizio_27_2;



import java.util.Scanner;

public class TestVideoPoker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mazzo mazzo = new Mazzo();

        // Distribuiamo le prime 5 carte
        Carta[] carteGiocatore = new Carta[5];
        for (int i = 0; i < 5; i++) {
            carteGiocatore[i] = mazzo.estraiCarta();
        }

        System.out.println("Le tue carte: ");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + carteGiocatore[i]);
        }

        // Chiediamo al giocatore quali carte scartare
        System.out.print("Scegli le carte da scartare (es. 1 3 5 per scartare la 1a, 3a, e 5a): ");
        String input = scanner.nextLine();
        String[] carteDaScartare = input.split(" ");

        for (String s : carteDaScartare) {
            int index = Integer.parseInt(s) - 1;
            carteGiocatore[index] = mazzo.estraiCarta();
        }

        // Mostriamo le carte finali
        System.out.println("Le tue carte finali: ");
        for (Carta carta : carteGiocatore) {
            System.out.println(carta);
        }

        // Determiniamo il punteggio
        ManoPoker manoPoker = new ManoPoker(carteGiocatore);
        String risultato = manoPoker.verificaMano();
        System.out.println("Hai ottenuto: " + risultato);
    }
}