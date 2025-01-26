package it.reactive.torneoDemoMongo.exception;

public class SquadraNonPresenteException extends CustomException {

    public SquadraNonPresenteException() {
        super("C4", "Squadra non presente");
    }
}
