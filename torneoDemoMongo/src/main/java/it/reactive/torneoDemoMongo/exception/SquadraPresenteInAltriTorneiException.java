package it.reactive.torneoDemoMongo.exception;

public class SquadraPresenteInAltriTorneiException extends CustomException {

    public SquadraPresenteInAltriTorneiException() {
        super("C4", "Squadra presente in altri tornei");
    }
}
