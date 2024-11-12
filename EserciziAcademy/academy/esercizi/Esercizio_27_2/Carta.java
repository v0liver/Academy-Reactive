package academy.esercizi.Esercizio_27_2;
public class Carta {
    private final Valore valore;
    private final Seme seme;

    public Carta(Valore valore, Seme seme) {
        this.valore = valore;
        this.seme = seme;
    }

    public Valore getValore() {
        return valore;
    }

    public Seme getSeme() {
        return seme;
    }

    @Override
    public String toString() {
        return valore.getNome() + " di " + seme.getNome();
    }
}
