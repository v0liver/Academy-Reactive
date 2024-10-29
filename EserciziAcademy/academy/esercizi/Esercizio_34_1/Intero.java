package academy.esercizi.Esercizio_34_1;

public class Intero extends OggettoMatematico implements Raddoppiabile, Triplicabile {
    private int v;

    public Intero(int v) {
        this.v = v;
    }

    @Override
    public double getValore() {
        return (double)v;
    }

    @Override
    public String stampa() {
        return String.valueOf(v);
    }

    @Override
    public void raddoppia() {
        v *= 2;

    }

    @Override
    public void triplica() {
        v *= 3;
    }
}
