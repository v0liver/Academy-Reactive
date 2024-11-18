package academy.esercizi.Esercizio_36_3;

public class Esercizio_36_3 {
    public static void main(String[] args) throws PrelievoNonValidoException {
        Esercizio_36_3 test = new Esercizio_36_3();
        test.soluzione();
    }

    public void soluzione() throws PrelievoNonValidoException {
        ContoCorrente contoCorrente = new ContoCorrente(100, "1568795652");
        contoCorrente.versa(2000);
        contoCorrente.versa(500);
        try {
            contoCorrente.preleva(200);
        } catch (PrelievoNonValidoException e) {
            throw new PrelievoNonValidoException("Prelievo non valido");
        }
        System.out.println("------------");
        try {
            contoCorrente.preleva(900);
        } catch (PrelievoNonValidoException e) {
            throw new PrelievoNonValidoException("Prelievo non valido");

        }

    }
}

