package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Schermo {
    @Value("${DIM_SCHERMO}")
    String dimSchermo;

    public String getSchermo(){
        return dimSchermo;
    }
}
