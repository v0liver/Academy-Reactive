package academy.esercizi.Eserczio_30_1;

public class MultipleChoiceQuestion extends Question {
    final int RISPOSTE_MULTIPLE = 3;
    String[] choices = new String[RISPOSTE_MULTIPLE];
    int[] positionCorrect = new int[RISPOSTE_MULTIPLE];

    @Override
    boolean checkAnswer(String rispostaData) {
        System.out.println("La risposta data è: " + rispostaData);
        String[] nuovastringa = rispostaData.split(",");
        int quantiUguali = 0;
        for (int i = 0; i < positionCorrect.length; i++) {
            for (int j = 0; j < nuovastringa.length; j++) {
                if (choices[i].equalsIgnoreCase(nuovastringa[j])) {
                    quantiUguali++;
                }

            }
        }
        return quantiUguali == nuovastringa.length;
    }

    @Override
    void display() {
        System.out.println(text);
        for (String choice : choices) {
            int i = 1;
            System.out.println(i + "." + choice);
            i++;
        }
    }

    public void setChoice(String risposta, int posizione, boolean isCorretto) {

        choices[posizione] = risposta;
        if (isCorretto) {
            positionCorrect[posizione] = posizione;
        }


    }
}
