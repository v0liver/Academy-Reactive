package academy.esercizi.Esercizio_33_1;


public class Esercizio_33_1 {
    public static void main(String[] args) {
        Esercizio_33_1 test = new Esercizio_33_1();
        test.soluzione();
    }

    public void soluzione() {
        int[] numbers = {1000, -2625, 10000000, 68};

        NumberFormatter.stampa(numbers, new DefaultFormatter());

        NumberFormatter.stampa(numbers, new DecimalSeparatorFormatter());

        NumberFormatter.stampa(numbers, new AccountingFormatter());

        NumberFormatter.stampa(numbers, new BaseFormatter(16));
    }
}
