package academy.esercizi.Eserczio_37_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FillInQuestion extends Question<String, List<String>> {
    public FillInQuestion(String text, List<String> answer) {
        super(text, answer);
        if (contaRisposteDaValorizzare(text) != answer.size()) {
            throw new IllegalArgumentException();
        }
    }

    public int contaRisposteDaValorizzare(String text) {
        int contatore = 0;
        String tmp = text;
        while (tmp.contains("****")) {
            tmp = tmp.replaceFirst("\\*\\*\\*\\*", "");
            contatore++;
        }
        return contatore;
    }

    @Override
    boolean checkAnswer(List<String> risposteData) {
        return risposteData.equals(answer);
    }

    @Override
    boolean rispondi(Scanner scanner) {
        String[] tmp = new String[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            tmp[i] = scanner.next();
        }
        List<String> risposteDate = new ArrayList<>(Arrays.asList(tmp));

        if (checkAnswer(risposteDate)) {
            System.out.println("Risposta corretta!");
            return true;
        } else {
            String text2 = text;
            for (String elemento : risposteDate) {
                text2 = text.replaceFirst("\\*\\*\\*\\*", elemento);
            }
            System.out.printf("Risposta: %s non corretta Riprova!", text2);
            return false;
        }

    }


}




