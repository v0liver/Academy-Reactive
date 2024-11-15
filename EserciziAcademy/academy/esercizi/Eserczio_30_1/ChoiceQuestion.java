package academy.esercizi.Eserczio_30_1;

public class ChoiceQuestion extends Question {
    final int RISPOSTE_MULTIPLE = 3;
    String[] choices = new String[RISPOSTE_MULTIPLE];
    int positionCorrect;

    @Override
    boolean checkAnswer(String rispostaData) {
        System.out.println("La risposta data è: " + rispostaData);
        return rispostaData.equalsIgnoreCase(choices[positionCorrect]);
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

    public void setChoice(String risposta, int posizione, boolean isCorretto) {

        choices[posizione] = risposta;
        if (isCorretto) {
            positionCorrect = posizione;
        }


    }
}
