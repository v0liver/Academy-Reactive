package academy.esercizi.Esercizio_36_3;

public class PrelievoNonValidoException extends Exception {

    public PrelievoNonValidoException(double saldoCorrente, String numeroConto, double importoDaPrelevare) {

        System.out.printf("Impossibile prelevare %f in quanto il conto %s ha una disponibilità di soli %f",
                importoDaPrelevare, numeroConto, saldoCorrente);
        System.out.println();
    }

    public PrelievoNonValidoException(String message) {
        super(message);
    }

}

