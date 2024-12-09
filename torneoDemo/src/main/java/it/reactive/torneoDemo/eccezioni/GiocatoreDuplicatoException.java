package it.reactive.torneoDemo.eccezioni;

public class GiocatoreDuplicatoException extends CustomException {

    public GiocatoreDuplicatoException() {
        super("C3", "Giocatore non trovato");
    }
}

