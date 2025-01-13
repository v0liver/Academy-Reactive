package it.reactive.torneoDemo.exception;

public class TorneoDuplicatoException extends CustomException {

    public TorneoDuplicatoException() {
        super("C1", "Torneo gia censito");
    }
}
