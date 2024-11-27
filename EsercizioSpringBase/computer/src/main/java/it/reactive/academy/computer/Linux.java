package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("linux")
public class Linux implements SistemaOperativo{
    @Value("${sistemaoperativo.linguaggio}")
    String linguaggioSistemaOperativo;

    public String getNome(){
        return "linux";
    }

    public String getLinguaggio(){
        return linguaggioSistemaOperativo;
    }
}
