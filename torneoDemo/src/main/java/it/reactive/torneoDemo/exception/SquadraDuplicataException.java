package it.reactive.torneoDemo.exception;

public class SquadraDuplicataException extends RuntimeException {
    public SquadraDuplicataException() {
        super("Squadra già censita");
    }
}
