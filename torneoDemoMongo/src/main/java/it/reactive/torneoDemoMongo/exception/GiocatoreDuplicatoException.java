package it.reactive.torneoDemoMongo.exception;

public class GiocatoreDuplicatoException extends CustomException {

    public GiocatoreDuplicatoException() {
        super("C3", "Giocatore non trovato");
    }
}

