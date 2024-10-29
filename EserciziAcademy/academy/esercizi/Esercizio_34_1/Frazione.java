package academy.esercizi.Esercizio_34_1;

public class Frazione extends OggettoMatematico implements Raddoppiabile, Triplicabile {
    private int numeratore;
    private int denominatore;

    public Frazione(int numeratore, int denominatore) {
        this.numeratore = numeratore;
        this.denominatore = denominatore;
    }

    boolean isFrazionePropria() {
        return numeratore % denominatore == 0;
    }

    Frazione inversa() {
        return new Frazione(denominatore,numeratore) ;
    }

    public int getNumeratore() {
        return numeratore;
    }

    public int getDenominatore() {
        return denominatore;
    }

    @Override
    public double getValore() {

        return   (((double)numeratore) / denominatore);
    }

    @Override
    public String stampa() {

        return numeratore + "/" + denominatore;
    }

    @Override
    public void raddoppia() {
        v *= 2;

    }

    @Override
    public boolean isDimezzabile() {
        return numeratore % 2 == 0;
    }

    @Override
    public void triplica() {
        v *= 3;

    }

}