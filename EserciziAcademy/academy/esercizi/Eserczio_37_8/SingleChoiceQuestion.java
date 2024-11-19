package academy.esercizi.Eserczio_37_8;

import java.util.Scanner;

public class SingleChoiceQuestion<A> extends Question<String, A> {

    String[] choices;


    public SingleChoiceQuestion(String text, A answer, String... choices) {
        super(text, answer);
        this.choices = choices;
    }


    @Override
    boolean rispondi(Scanner scanner) {
        System.out.println("Inserisci una risposta tra le proposte date.");
        Integer rispostaUtente = Integer.valueOf(scanner.next());

        if (checkAnswer((A) rispostaUtente)) {
            System.out.println("Risposta corretta!!");
            return true;
        } else {
            System.out.printf("Risposta %d non corretta", rispostaUtente);
            return false;
        }
    }


    @Override
    boolean checkAnswer(A rispostaData) {
        System.out.println("La risposta data è: " + rispostaData);
        return rispostaData.equals(answer);
    }

    @Override
    void display() {
        System.out.println(text);
        int i = 1;
        for (String choice : choices) {
            System.out.println(i + "." + choice);
            i++;
        }

    }


}
