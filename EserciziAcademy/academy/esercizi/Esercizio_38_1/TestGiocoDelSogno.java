package academy.esercizi.Esercizio_38_1;

import java.util.Optional;
import java.util.Scanner;

public class TestGiocoDelSogno {
    public static void main(String[] args) {


    }
    private void soluzione() {
        Scanner scanner = new Scanner(System.in);
        GiocoDelSognoOptional giocoDelSegnoOptional = new GiocoDelSognoOptional();
        boolean fineGioco = false;
        Optional<Integer> valoreUtente;
        int numeroMassimo = 2000;

        System.out.println("Benvenuto nel Gioco del Segno!");
        System.out.println("Cerca di indovinare il valore! Puoi inserire 'null' per un valore assente.");
        do {
            System.out.println("Inserisci un numero da 1 ad un massimo di " + numeroMassimo + " o null per un valore assente:");
            String controlloNull = scanner.nextLine();
            numeroMassimo = numeroMassimo/2;

            if (controlloNull.equals("null")) {
                valoreUtente = Optional.empty();
            } else {
                try {
                    valoreUtente = Optional.of(Integer.parseInt(controlloNull));
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Per favore, inserisci un numero valido o 'null'.");
                    continue;
                }
            }
            fineGioco = giocoDelSegnoOptional.gioco(valoreUtente);

        } while (!fineGioco);
    }
}
