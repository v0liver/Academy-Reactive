package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurazione {

    @Value("${harddisk}")
    String valoreHarddisk;

    @Bean()
    public HardDisk crea(){
        if (valoreHarddisk.equals("hd")){
            return new HD();
        } else if (valoreHarddisk.equals("ssd")) {
            return new SSD();
        }
        else throw new RuntimeException("Variabile d'ambiente diversa da SSD o HD");
    }

}
