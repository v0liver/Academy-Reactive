
import academy.esercizi.Eserczio_37_8.Question;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class NumericQuestion extends Question<String,Number> {

    NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

    public NumericQuestion(String text, Number answer) {
        super(text, answer);
    }

//    @Override
//    public boolean checkAnswer(Number answer) throws ParseException {
//        return answer.equals((numberFormat.parse(String.valueOf(this.answer))) );
//    }

    @Override
    public boolean rispondi(Scanner scanner) throws ParseException {
        System.out.println("Inserisci una risposta tra quelle date: ");
        String risposta = scanner.nextLine();
        Number rispostaUtente = numberFormat.parse(risposta);
        if (!risposta.equals(String.valueOf(rispostaUtente))) throw new ParseException("Non corretto",1);
        if (checkAnswer(rispostaUtente)){
            System.out.println("Risposta Corretta");
            return true;
        }else {
            System.out.println("Risposta: " + rispostaUtente + " non corretta. Riprova" );
            return false;
        }
    }
    }