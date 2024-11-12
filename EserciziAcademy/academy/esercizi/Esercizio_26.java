package academy.esercizi;

import java.util.Random;
import java.util.Scanner;

public class Esercizio_26 {

    static final int NUMERO_DI_CARTE = 45;
    static int[] pile = new int[NUMERO_DI_CARTE];

    public static void main(String[] args) {
        Esercizio_26 test = new Esercizio_26();
        test.start(pile);

    }

    private void start(int[] pile) {
        System.out.print("Quanti mazzetti vuoi costruire?");
        Scanner scan = new Scanner(System.in);
        int mucchietti = scan.nextInt();
        gioca(pile, mucchietti);

    }

    private void gioca(int[] pile, int mucchiettiIniziali) {
        int contamosse = 0;
        configurazioneIniziale(pile, mucchiettiIniziali);
        do {
            muovi(pile);
            contamosse++;
        } while (!finito(pile));
        System.out.printf("Il gioco è finito in %d mosse", contamosse);
    }

    private void muovi(int[] pile) {


        int sommaCarte = 0;
        for (int i = 0; i < pile.length; i++) {
            if (pile[i] > 0) {
                pile[i]--;
                sommaCarte++;
            }
        }

        int i=0;
        while (pile[i]!=0) {
                i++;
            }
        pile[i]=sommaCarte;

        stampa(pile);

    }

    private void stampa(int[] pile) {
        for (int i : pile) {
            if (i != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

    }

    private int generaNumeroCasuale(int maxNumero) {
        Random random = new Random();
        return random.nextInt(maxNumero) + 1;
    }

    private void configurazioneIniziale(int[] pile, int mucchietti) {
        int somma = 0;
        int i;
        for ( i = 0; i < mucchietti-1; i++) {


            pile[i] = generaNumeroCasuale(NUMERO_DI_CARTE - somma- (mucchietti - (i + 1)));


            somma += pile[i];


        }
        pile[i]=NUMERO_DI_CARTE-somma;


        for (int numero : pile) {
            if (numero != 0) {
                System.out.print(numero + " ");
            }

        }
        System.out.println();


    }

    private boolean finito(int[] pile) {
        int[] arrayDiAppoggio = new int[9];
        for (int pila : pile) {
            if (pila >= 1 && pila <= 9) {
                arrayDiAppoggio[pila - 1] = 1;
            }
        }
        for (int numero : arrayDiAppoggio) {
            if (numero == 0) {
                return false;
            }
        }
        return true;
    }


}
