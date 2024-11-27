package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Windows implements SistemaOperativo {

    @Value("${sistemaoperativo.linguaggio}")
    String linguaggioSistemaOperativo;

    public String getNome(){
        return "windows";
    }

    public String getLinguaggio(){
        return linguaggioSistemaOperativo;
    }
}
