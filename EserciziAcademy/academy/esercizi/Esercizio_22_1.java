package academy.esercizi;

import java.util.Scanner;

public class Esercizio_22_1 {
    public static void main(String[] args) {

        Esercizio_22_1 test = new Esercizio_22_1();
        test.risolvi();


        }



    private void risolvi() {
        int resto;
        int sommaDaPagare;
        int quantitaDenaroPagato;


        Scanner scan = new Scanner(System.in);
        System.out.print("Inserisci la somma da pagare in penny: ");
        sommaDaPagare = scan.nextInt();
        System.out.print("");
        System.out.print("Inserisci la quantità di denaro in penny pagata del cliente: ");
        System.out.print("");
        quantitaDenaroPagato = scan.nextInt();

        resto = quantitaDenaroPagato - sommaDaPagare;

        while (resto<0){
            System.out.print("La quantità di denaro data non basta per pagare il conto.");
            System.out.println();
            System.out.print("Inserisci la quantità di denaro in penny pagata del cliente: ");
            quantitaDenaroPagato = scan.nextInt();
            resto = quantitaDenaroPagato - sommaDaPagare;
            System.out.println();
        }
        
        int dollari = resto / 100;
        resto = resto % 100;

        int quarter = resto / 25;
        resto = resto % 25;

        int dime = resto / 10;
        resto = resto % 10;

        int nickel = resto / 5;
        resto = resto % 5;

        int centesimo = resto;


        System.out.printf("Il resto è di %d dollari, %d quarter, %d dime, %d nickel e %d centesimi", dollari, quarter, dime, nickel, centesimo);

    }
}

