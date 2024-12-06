package it.reactive.torneoDemo.exception;

public class SquadraNonPresenteExceptionException extends RuntimeException {
    public SquadraNonPresenteExceptionException() {
        super("Squadra non presente");
    }
}
