package academy.esercizi.Esercizio_36bis_1;

 public class Agenda implements Comparable<Agenda> {




     public enum Giorni {LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI, VENERDI, SABATO, DOMENICA};
    public enum Impegni{TEATRO, CALCETTO, DIVANO}
    Giorni giornoDellaSettimana;
    Impegni impegno;



     public Agenda(Giorni giornoDellaSettimana, Impegni impegno) {
        this.giornoDellaSettimana = giornoDellaSettimana;
        this.impegno = impegno;
    }

     @Override
     public int compareTo(Agenda o) {
         return this.giornoDellaSettimana.compareTo(o.giornoDellaSettimana);
     }
}
