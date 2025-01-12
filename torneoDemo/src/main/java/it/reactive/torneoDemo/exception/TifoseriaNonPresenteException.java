package it.reactive.torneoDemo.exception;

public class TifoseriaNonPresenteException extends CustomException {
    public TifoseriaNonPresenteException() {
        super("C4", "Tifoseria non presente");
    }
}
