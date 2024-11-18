package academy.esercizi.Esercizio_33_1;

public class DecimalSeparatorFormatter implements NumberFormatter {
    @Override
    public String format(int n) {
        String nStringa = String.valueOf(n);
        StringBuilder stringBuilder = new StringBuilder();

        int length = nStringa.length();
        int count = 0;


        for (int i = length - 1; i >= 0; i--) {
            stringBuilder.append(nStringa.charAt(i));
            count++;


            if (count == 3 && i != 0) {
                stringBuilder.append(",");
                count = 0;
            }
        }


        nStringa = stringBuilder.reverse().toString();
        return nStringa;
    }
}
