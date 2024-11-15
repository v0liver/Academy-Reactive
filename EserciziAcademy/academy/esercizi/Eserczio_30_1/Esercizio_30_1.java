package academy.esercizi.Eserczio_30_1;

public class Esercizio_30_1 {
    public static void main(String[] args) {
        Esercizio_30_1 test = new Esercizio_30_1();
        test.soluzione();
    }

    public void soluzione() {
        Question question1 = new FillInQuestion();
        question1.setText("La capitale dell'Italia è ****, si trova nella regione **** ed ha **** abitanti");
        question1.setAnswer("La capitale dell'Italia è Roma, si trova nella regione Lazio ed ha 2800000 abitanti");
        question1.display();
        System.out.println(question1.checkAnswer("Roma,Lazio,2800000"));

        Question question2 = new NumericQuestion();
        question2.setText("Qual è la radice quadrata di 16?");
        question2.setAnswer("4");
        question2.display();
        System.out.println(question2.checkAnswer("4"));

        Question question3 = new FreeResponse();
        question3.setText("Quale è la capitalia dell’Italia?");
        question3.setAnswer("Roma");
        question3.display();
        System.out.println(question3.checkAnswer("Roma"));

        ChoiceQuestion question4 = new ChoiceQuestion();
        question4.setText("Come si chiama il nostro professore?");
        question4.setChoice("Daniele", 0, true);
        question4.setChoice("Mario", 1, false);
        question4.setChoice("Luigi", 2, false);
        question4.display();
        System.out.println(question4.checkAnswer("Daniele"));

        MultipleChoiceQuestion question5 = new MultipleChoiceQuestion();
        question5.setText("Come si chiama il nostro professore?");
        question5.setChoice("Daniele", 0, true);
        question5.setChoice("Mario", 1, true);
        question5.setChoice("Luigi", 2, false);
        question5.display();
        System.out.println(question5.checkAnswer("Daniele,Mario"));
    }
}
