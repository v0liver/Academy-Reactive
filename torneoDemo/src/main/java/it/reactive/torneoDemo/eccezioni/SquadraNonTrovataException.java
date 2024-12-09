package it.reactive.torneoDemo.eccezioni;

public class SquadraNonTrovataException extends CustomException {

    public SquadraNonTrovataException(String message) {
        super("C5","Squadra non trovata");
    }
}
