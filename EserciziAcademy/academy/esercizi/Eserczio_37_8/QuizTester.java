package academy.esercizi.Eserczio_37_8;


import java.text.ParseException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class QuizTester {

    public static void main(String[] args) {
        QuizTester tester = new QuizTester();
        tester.start();
    }

    private void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            verifica(scanner, new FillInQuestion("La capitale d'Italia è ****, è nella regione **** ed ha **** abitanti.", Arrays.asList("Roma", "Lazio", "2800000")));
            verifica(scanner, new FreeResponse("Quale è la capitale d'Italia?", "Roma"));
            verifica(scanner, new SingleChoiceQuestion<>("Quale è la capitale d'Italia?", 1, "Roma", "Milano",
                    "Torino"));
            verifica(scanner, new MultipleChoiceQuestion("Quali città hanno più di un milione di abitanti?", Arrays.asList(1, 2), "Roma", "Milano", "Torino"));
            verifica(scanner, new NumericQuestion("Quanti abitanti ha Roma?", 2800000));
            try {
                verifica(scanner, new FillInQuestion("Le squadre della serie A che hanno, a fine 2023, almeno 2 stelle sono **** e ****.", Arrays.asList("Juventus")));
            } catch (IllegalArgumentException e) {
                System.out.println("La definizione della domanda non è coerente con il numero di risposte.");
            }
        }
    }

    private void verifica(Scanner scanner, Question<?,?> question) {
        boolean b;
        do {
            question.display();

                b = question.rispondi(scanner);

        } while (!b);
    }
}

