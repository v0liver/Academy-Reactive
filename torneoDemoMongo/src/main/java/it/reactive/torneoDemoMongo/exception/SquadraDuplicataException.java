package it.reactive.torneoDemoMongo.exception;

public class SquadraDuplicataException extends CustomException {

    public SquadraDuplicataException() {
        super("C1", "Squadra gia censita");
    }
}
