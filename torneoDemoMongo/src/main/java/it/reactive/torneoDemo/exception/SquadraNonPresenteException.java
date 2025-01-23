package it.reactive.torneoDemo.exception;

public class SquadraNonPresenteException extends CustomException {

    public SquadraNonPresenteException() {
        super("C4", "Squadra non presente");
    }
}
