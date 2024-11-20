package academy.esercizi.Eserczio_37_8;

import java.text.ParseException;
import java.util.Scanner;

public abstract class Question<T, A> {
    public T text;
    public A answer;

    public Question(T text, A answer) {
        this.text = text;
        this.answer = answer;
    }

    public void setText(T text) {
        this.text = text;
    }






    abstract boolean rispondi(Scanner scanner) ;

    abstract boolean checkAnswer(A rispostaData) throws ParseException;

    void display() {
        System.out.println(text);
    }




}
