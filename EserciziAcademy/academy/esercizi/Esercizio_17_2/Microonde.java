package academy.esercizi.Esercizio_17_2;

public class Microonde {
    public static void main(String[] args) {
        PannelloDiControllo microonde = new PannelloDiControllo();

        microonde.piuTrentasecondi();
        microonde.cambiareLivelloPotenza();
        microonde.start();

        microonde.reset();

        microonde.start();
    }
}
