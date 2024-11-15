package academy.esercizi.Eserczio_30_1;

public abstract class Question {
    public String text;
    public String answer;

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }



    public String getAnswer() {
        return answer;
    }

    abstract boolean checkAnswer(String rispostaData);
    abstract void display();


}
