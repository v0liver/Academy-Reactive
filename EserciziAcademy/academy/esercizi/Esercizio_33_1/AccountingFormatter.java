package academy.esercizi.Esercizio_33_1;

public class AccountingFormatter implements NumberFormatter {
    @Override
    public String format(int n) {
        if (n < 0) {
            return "(" + Math.abs(n) + ")";
        }
        return String.valueOf(n);
    }
}
