package it.reactive.torneoDemo.eccezioni;

public class SquadraDuplicataException extends CustomException {

    public SquadraDuplicataException() {
        super("C1", "Squadra gia censita");
    }
}
