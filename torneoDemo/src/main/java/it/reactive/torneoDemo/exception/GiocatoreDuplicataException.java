package it.reactive.torneoDemo.exception;

public class GiocatoreDuplicataException extends RuntimeException {
    public GiocatoreDuplicataException() {
        super("Giocatore già censito");
    }
}
