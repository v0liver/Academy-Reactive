package academy.esercizi.Eserczio_30_1;

public class FillInQuestion extends Question {
    @Override
    boolean checkAnswer(String rispostaData) {
        System.out.println("La risposta data è: " + rispostaData);
        String[] risposta = rispostaData.split(",");
        String nuovaStringa = text;
        for (String s : risposta) {
            nuovaStringa = nuovaStringa.replaceFirst("\\*\\*\\*\\*", s);
        }
        System.out.println(nuovaStringa);
        return nuovaStringa.equalsIgnoreCase(getAnswer());
    }

    @Override
    void display() {
        System.out.println(text);
    }
}
