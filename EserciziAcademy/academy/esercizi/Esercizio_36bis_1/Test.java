package academy.esercizi.Esercizio_36bis_1;


public class Test {
    public static void main(String[] args) {
        Test prova = new Test();
        prova.soluzione();


    }

    public void soluzione() {

        Agenda ag1 = new Agenda(Agenda.Giorni.MARTEDI, Agenda.Impegni.TEATRO);
        Agenda ag2 = new Agenda(Agenda.Giorni.LUNEDI, Agenda.Impegni.CALCETTO);
        Agenda ag3 = new Agenda(Agenda.Giorni.DOMENICA, Agenda.Impegni.DIVANO);
        Agenda ag4 = new Agenda(Agenda.Giorni.LUNEDI, Agenda.Impegni.TEATRO);
        PrioritaAttivita prioritaAttivita = new PrioritaAttivita();
        System.out.printf("%s %s %d\n", ag1.giornoDellaSettimana, ag2.giornoDellaSettimana, prioritaAttivita.compare(ag1, ag2));
        System.out.printf("%s %s %d\n", ag1.giornoDellaSettimana, ag3.giornoDellaSettimana, prioritaAttivita.compare(ag1, ag3));
        System.out.printf("%s %s %d\n", ag2.giornoDellaSettimana, ag3.giornoDellaSettimana, prioritaAttivita.compare(ag2, ag3));
        System.out.printf("%s %s %d\n", ag1.giornoDellaSettimana, ag4.giornoDellaSettimana, prioritaAttivita.compare(ag1, ag4));
        System.out.printf("%s %s %d\n", ag2.giornoDellaSettimana, ag4.giornoDellaSettimana, prioritaAttivita.compare(ag2, ag4));
    }
}
