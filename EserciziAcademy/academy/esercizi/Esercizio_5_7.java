package academy.esercizi;
public class Esercizio_5_7 {
    public static void main(String[] args) {
        String creditCardNumber = "4123-5678-9012-3450";
        String risultato = rimuoviSpaziETrattini(creditCardNumber);
        System.out.println(risultato);
    }

    private static String rimuoviSpaziETrattini(String numero) {
        StringBuilder risultato = new StringBuilder();

        for (int i = 0; i < numero.length(); i++) {
            char c = numero.charAt(i);
            if (c != ' ' && c != '-') {
                risultato.append(c);
            }
        }

        return risultato.toString();
    }
}
