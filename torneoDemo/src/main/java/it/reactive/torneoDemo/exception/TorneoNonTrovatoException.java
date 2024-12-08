package it.reactive.torneoDemo.exception;

public class TorneoNonTrovatoException extends RuntimeException {
    public TorneoNonTrovatoException() {
        super("Torneo non trovato");
    }
}
