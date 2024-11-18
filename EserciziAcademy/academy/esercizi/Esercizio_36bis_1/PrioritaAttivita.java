package academy.esercizi.Esercizio_36bis_1;

import java.util.Comparator;

public class PrioritaAttivita implements Comparator<Agenda> {


    public int compare(Agenda o1, Agenda o2) {
        if (o1.impegno.ordinal() < o2.impegno.ordinal()) {
            System.out.println("L'agenda " + o1.giornoDellaSettimana + " " + o1.impegno + " è più importante dell' agenda " + o2.giornoDellaSettimana + " " + o2.impegno);
            return 1;
        } else if (o1.impegno.ordinal() > o2.impegno.ordinal()) {
            System.out.println("L'agenda " + o1.giornoDellaSettimana + " " + o1.impegno + " è meno importante dell' agenda " + o2.giornoDellaSettimana + " " + o2.impegno);
            return -1;
        } else {
            System.out.println("Le agende " + o1.giornoDellaSettimana + " " + o1.impegno + " e " + o2.giornoDellaSettimana + " " + o2.impegno + " sono di parità importanza.");
            return 0;
        }
    }
}
