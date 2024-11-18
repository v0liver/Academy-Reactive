package academy.esercizi.Esercizio_33_1;

public interface NumberFormatter {
    static void stampa(int[] numbers, NumberFormatter numberFormatter) {
        for (int number : numbers) {
            System.out.printf("Numero formattato  %10s", numberFormatter.format(number));
            System.out.println();
        }
    }

    String format(int n);


}
