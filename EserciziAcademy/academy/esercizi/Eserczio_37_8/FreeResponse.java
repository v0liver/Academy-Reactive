package academy.esercizi.Eserczio_37_8;

import java.util.Scanner;

public class FreeResponse extends Question<String,String> {
    public FreeResponse(String text, String answer) {
        super(text, answer);
    }

    @Override
    boolean rispondi(Scanner scanner) {
        System.out.println("Digita una risposta.");
        String risposta = scanner.next();
       if (checkAnswer(risposta)){
           System.out.println("La risposta è corretta!");
           return true;
       }else {
           System.out.printf("Risposta %s non corretta. Riprova!",risposta);
           return false;
       }

    }

    @Override
    boolean checkAnswer(String rispostaData) {
        return rispostaData.equalsIgnoreCase(answer);
    }

}
