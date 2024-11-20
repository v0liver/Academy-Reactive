package academy.esercizi.Eserczio_37_8;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class NumericQuestion extends Question<String, Number> {

    NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

    public NumericQuestion(String text, Number answer) {
        super(text, answer);
    }

    @Override
    public boolean checkAnswer(Number answer)  {
        try {
            return answer.equals((numberFormat.parse(String.valueOf(this.answer))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean rispondi(Scanner scanner)  {
        Number rispostaUtente=null;
        boolean flag = false;
        do {
            try {
                System.out.println("Inserisci una risposta");
                String risposta = scanner.next();

                rispostaUtente = numberFormat.parse(risposta);

                if (!risposta.equals(String.valueOf(rispostaUtente))) {
                    throw new ParseException("Non corretto", 1);
                }
                if (checkAnswer(rispostaUtente)) {
                    System.out.println("Risposta Corretta");
                    flag = true;

                } else {
                    System.out.println("Risposta: " + rispostaUtente + " non corretta. Riprova");
                }
            } catch (ParseException e) {
                System.err.println(e.getMessage());

            }
        } while (!flag);
        return checkAnswer(rispostaUtente);
    }
}