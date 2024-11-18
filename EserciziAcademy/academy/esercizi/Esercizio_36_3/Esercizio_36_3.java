package academy.esercizi.Esercizio_36_3;

public class Esercizio_36_3 {
    public static void main(String[] args)  {
        Esercizio_36_3 test = new Esercizio_36_3();
        test.soluzione();
    }

    public void soluzione()  {
        ContoCorrente contoCorrente = new ContoCorrente(100, "1568795652");
        contoCorrente.versa(2000);
        System.out.println("------------");
        contoCorrente.versa(500);
        System.out.println("------------");
        try {
            contoCorrente.preleva(200);
        } catch (PrelievoNonValidoException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------");
        try {
            contoCorrente.preleva(900);
        } catch (PrelievoNonValidoException e) {
            System.out.println(e.getMessage());

        }

    }
}

