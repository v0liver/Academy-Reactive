package it.reactive.torneoDemo.eccezioni;

public class TorneoNonTrovatoException extends CustomException {
    public TorneoNonTrovatoException() {
        super("C2","Torneo non trovato");
    }
}
