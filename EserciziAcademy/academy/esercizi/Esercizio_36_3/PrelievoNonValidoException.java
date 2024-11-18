package academy.esercizi.Esercizio_36_3;

public class PrelievoNonValidoException extends Exception {

    public PrelievoNonValidoException(double saldoCorrente, String numeroConto, double importoDaPrelevare, String message) {
        super(message);
        System.out.printf("Numero conto: %s\nSaldo conto: %f\nImporto da prelevare: %f",numeroConto,
                saldoCorrente, importoDaPrelevare);
        System.out.println();
    }



}

