package academy.esercizi.Esercizio_34_1;

public class Test {
    public static void main(String[] args) {
        Raddoppiabile intero = new Intero(12);
        intero.raddoppia();
        Operazione o = new Operazione( ((OggettoMatematico) intero).getValore(),
                new Frazione(15, 3).getValore(), '+');

        System.out.println(Raddoppiabile.descrivi());
        try {
            System.out.println(intero.isDimezzabile());
        } catch (Exception e) {
            System.out.println("ERRORE");
        }

        System.out.println(o.stampa());

        Triplicabile secondo = new Intero(18);
        secondo.triplica();
        System.out.println(((OggettoMatematico) secondo).getValore());

        Frazione f = new Frazione(2, 20);
        System.out.println(f.inversa().getValore());
        System.out.println(f.isDimezzabile());

    }
}
