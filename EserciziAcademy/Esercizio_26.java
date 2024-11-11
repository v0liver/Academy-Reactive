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
        for (int i = 0; sommaCarte > 0; i++) {
            if (pile[i] == 0) {
                pile[i] = sommaCarte;
                sommaCarte = 0;
            }
        }

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
        int carteMassime = NUMERO_DI_CARTE;
        int somma = 0;

        for (int i = 0; i < mucchietti; i++) {
            //pile[i] = generaNumeroCasuale(carteMassime - (mucchietti - i));

            pile[i] = generaNumeroCasuale(carteMassime - (mucchietti - (i + 1)));

            somma += pile[i];
            // carteMassime -= pile[i];
            carteMassime = NUMERO_DI_CARTE - somma;

        }
        //pile[mucchietti - 1] = carteMassime;

        for (int i : pile) {
            if (i != 0) {
                System.out.print(i + " ");
            }

        }
        System.out.println();


    }

    private boolean finito(int[] pile) {
        int[] arrayDiAppoggio = new int[9];

        for (int i = 1; i <= 9; i++) {
            boolean numeroEsistente = false;
            for (int j = 0; j < pile.length-1; j++) {
                if (pile[j] == i && !numeroEsistente) {
                    arrayDiAppoggio[j] = 1;
                    numeroEsistente=true;

                }

            }

        }
        return checkFine(arrayDiAppoggio);
    }

    private boolean checkFine(int[] arrayDiAppoggio) {
        int quanteVolteUno = 0;

        int[] arraydiprova = new int[45];
        int i = 0;

        for (int numero : arrayDiAppoggio) {

            if (numero == 1 ) {
                quanteVolteUno++;
                arraydiprova[i] = numero;
                i++;


            }
            if (quanteVolteUno == 9) {
                for (int arraydiprovum : arraydiprova) {
                    System.out.print(arraydiprovum + " ");
                }

            }

        }
        return quanteVolteUno == 9;
    }
}
