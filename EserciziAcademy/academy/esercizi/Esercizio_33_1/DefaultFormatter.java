package academy.esercizi.Esercizio_33_1;

public class DefaultFormatter implements NumberFormatter {
    @Override
    public String format(int n) {
        return String.valueOf(n);
    }
}
