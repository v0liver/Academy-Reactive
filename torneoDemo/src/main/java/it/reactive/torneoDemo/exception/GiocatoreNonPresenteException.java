package it.reactive.torneoDemo.exception;

public class GiocatoreNonPresenteException extends CustomException {
    public GiocatoreNonPresenteException() {
        super("C4","Giocatore non presente");
    }
}
