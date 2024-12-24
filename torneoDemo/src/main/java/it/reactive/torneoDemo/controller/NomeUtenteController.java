package it.reactive.torneoDemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NomeUtenteController {
    // aggiunta di una get per il nome
    @GetMapping("/applicazione")
    public String getNome() {
        return "Vito_Oliveri";
    }
}