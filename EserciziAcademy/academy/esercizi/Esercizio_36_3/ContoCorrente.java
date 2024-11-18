package academy.esercizi.Esercizio_36_3;

public class ContoCorrente {
    double saldo;
    String numeroConto;
    final int IMPORTO_MAX = 1000;


    public ContoCorrente(double saldoIniziale, String numeroConto) {
        this.saldo = saldoIniziale;
        this.numeroConto = numeroConto;
    }

    public void versa(double importo) {
        try {
            if (importo > IMPORTO_MAX) {
                throw new SuperioreMilleException("Il versamento che stai cercando di fare è superiore a 1000 euro.", -1);
            }
            this.saldo += importo;
            System.out.println("Versamento di " + importo + " euro effettuato. Saldo attuale: " + saldo + " euro.");
        } catch (SuperioreMilleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void preleva(double importo) throws PrelievoNonValidoException {
        if (importo > saldo) {
            throw new PrelievoNonValidoException(saldo, numeroConto, importo);
        }
        saldo -= importo;
        System.out.println("Prelievo di " + importo + " euro effettuato. Saldo attuale: " + saldo + " euro.");
    }
}
