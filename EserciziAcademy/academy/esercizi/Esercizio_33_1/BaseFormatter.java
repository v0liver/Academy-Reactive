package academy.esercizi.Esercizio_33_1;

public class BaseFormatter implements NumberFormatter {
    private final int base;

    public BaseFormatter(int base) {
        this.base = base;
    }

    @Override
    public String format(int n) {
        if (base < 2 || base > 36) {
            return "Errore: La base deve essere tra 2 e 36.";
        }
        return Integer.toString(n, base);


    }

}


