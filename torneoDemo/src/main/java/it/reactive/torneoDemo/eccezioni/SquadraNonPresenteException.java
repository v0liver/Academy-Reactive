package it.reactive.torneoDemo.eccezioni;

public class SquadraNonPresenteException extends CustomException {

    public SquadraNonPresenteException() {
        super("C4","Squadra non presente");
    }
}
