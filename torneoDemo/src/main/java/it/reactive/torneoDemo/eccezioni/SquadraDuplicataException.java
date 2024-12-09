package it.reactive.torneoDemo.eccezioni;

public class SquadraDuplicataException extends CustomException {

    public SquadraDuplicataException(String codErr, String messaggio) {
        super("C1", "Squadra gia censita");
    }
}
