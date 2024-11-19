package academy.esercizi.Eserczio_37_8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleChoiceQuestion extends SingleChoiceQuestion<List<Integer>> {
    String[] choices;

    public MultipleChoiceQuestion(String text, List<Integer> answer, String[] choices) {
        super(text, answer, choices);
    }

    @Override
    boolean checkAnswer(List<Integer> risposteData) {
        return risposteData.equals(answer);
    }


    boolean rispondi(Scanner scanner) {
        System.out.println("Inserisci i numeri delle risposte corrette e digita 0 quando hai finito.");
        List<Integer> risposteUtente = new ArrayList<>();
        boolean controllo = true;
        while (controllo){
            int numero = scanner.nextInt();
            if ( numero !=0 ){
                risposteUtente.add(numero);
            }else{
                controllo = false;
            }
        }
        if (checkAnswer(risposteUtente)){
            System.out.println("Risposta corretta!!");
            return true;
        }else {
            for (int i = 0; i < risposteUtente.size(); i++) {
                System.out.printf("Risposta %d non corretta. Riprova",risposteUtente.get(i));
            }
            return false;
        }

    }
}
