package academy.esercizi.Esercizio_17_2;

public class PannelloDiControllo {
    private boolean potenza = true;
    private int tempoInsecondi;

    public void piuTrentasecondi() {
        tempoInsecondi += 30;

    }

    public void cambiareLivelloPotenza() {
        this.potenza = !potenza;

    }

    public void reset() {
        this.potenza = true;
        this.tempoInsecondi = 0;
    }

    public void start() {
        System.out.printf("Cooking for %d seconds at level %d", tempoInsecondi, potenza ? 1 : 2);
        System.out.println();

    }
}
