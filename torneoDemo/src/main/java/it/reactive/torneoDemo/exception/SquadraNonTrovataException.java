package it.reactive.torneoDemo.exception;

public class SquadraNonTrovataException extends RuntimeException {
    public SquadraNonTrovataException() {
        super("Squadra non trovata");
    }
}
