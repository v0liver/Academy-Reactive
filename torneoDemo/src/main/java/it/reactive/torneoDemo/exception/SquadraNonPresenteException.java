package it.reactive.torneoDemo.exception;

public class SquadraNonPresenteException extends RuntimeException {
    public SquadraNonPresenteException() {
        super("Squadra non presente");
    }
}
