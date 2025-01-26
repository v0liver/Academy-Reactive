package it.reactive.torneoDemoMongo.exception;

public class TorneoConPiuSquadreException extends CustomException {

    public TorneoConPiuSquadreException() {
        super("C1", "Il Torneo ha più di una squadra all'interno.");
    }
}
