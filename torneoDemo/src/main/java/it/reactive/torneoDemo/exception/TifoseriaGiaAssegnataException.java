package it.reactive.torneoDemo.exception;

public class TifoseriaGiaAssegnataException extends CustomException {

    public TifoseriaGiaAssegnataException() {
        super("C5", "Tifoseria già assegnata ad un'altra squadra");
    }
}

