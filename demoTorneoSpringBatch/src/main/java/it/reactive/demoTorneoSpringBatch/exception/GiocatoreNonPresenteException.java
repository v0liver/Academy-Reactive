package it.reactive.demoTorneoSpringBatch.exception;

public class GiocatoreNonPresenteException extends CustomException {
    public GiocatoreNonPresenteException() {
        super("C4", "Giocatore non presente");
    }
}
