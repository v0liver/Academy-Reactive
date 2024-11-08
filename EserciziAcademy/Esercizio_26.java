import java.util.Random;
import java.util.Scanner;

public class Esercizio_26 {

    static final int NUMERO_DI_CARTE = 45;
    static int[] pile = new int[NUMERO_DI_CARTE];

    public static void main(String[] args) {
        Esercizio_26 test = new Esercizio_26();
        test.configurazioneIniziale(pile, 5);

    }

    private void start() {
        Scanner scan = new Scanner(System.in);


    }

    private void gioca(int mucchiettiIniziali) {
        int contamosse = 0;
        do {
            contamosse++;
        } while (finito());
        System.out.printf("Il gioco è finito in %d mosse", contamosse);
    }

    private int generaNumeroCasuale(int maxNumero) {
        Random random = new Random();
        return random.nextInt(maxNumero) + 1;
    }

    private void configurazioneIniziale(int[] pile, int mucchietti) {
        int carteMassime = NUMERO_DI_CARTE;


        for (int i = 0; i < mucchietti - 1; i++) {
            pile[i] = generaNumeroCasuale(carteMassime - (mucchietti - i));
            carteMassime -= pile[i];

        }
        pile[mucchietti - 1] = carteMassime;
        for (int i : pile) {
            System.out.println(i);
        }
    }

    private boolean finito() {
        //todo

        return true;
    }
}
