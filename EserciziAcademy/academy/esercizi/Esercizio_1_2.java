package academy.esercizi;

public class Esercizio_1_2 {
    public static void main(String[] args) {
         double saldoConto= 1000;
         double interesse = 0.05;

        for (int anno = 1; anno <=3; anno++) {
            saldoConto += saldoConto*interesse;
            System.out.printf("Il saldo del %d anno è: %.2f%n", anno,saldoConto);
        }


    }
}
