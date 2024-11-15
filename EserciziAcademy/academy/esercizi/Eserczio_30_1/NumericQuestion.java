package academy.esercizi.Eserczio_30_1;

public class NumericQuestion extends Question {
    @Override
    boolean checkAnswer(String rispostaData) {
        System.out.println("La risposta data è: " + rispostaData);
        return rispostaData.equalsIgnoreCase(getAnswer());
    }

    @Override
    void display() {
        System.out.println(text);
    }
}
