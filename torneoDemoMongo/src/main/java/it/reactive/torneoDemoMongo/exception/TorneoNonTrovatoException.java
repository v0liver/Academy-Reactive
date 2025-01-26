package it.reactive.torneoDemoMongo.exception;

public class TorneoNonTrovatoException extends CustomException {
    public TorneoNonTrovatoException() {
        super("C2", "Torneo non trovato");
    }
}
