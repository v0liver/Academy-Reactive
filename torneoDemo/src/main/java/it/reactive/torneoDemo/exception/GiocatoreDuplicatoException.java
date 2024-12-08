package it.reactive.torneoDemo.exception;

public class GiocatoreDuplicatoException extends RuntimeException {
    public GiocatoreDuplicatoException() {
        super("Giocatore già censito");
    }
}
