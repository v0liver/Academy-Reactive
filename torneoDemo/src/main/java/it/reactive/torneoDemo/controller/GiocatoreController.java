package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.Service.GiocatoreService;
import it.reactive.torneoDemo.resource.GiocatoreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "giocatori", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class GiocatoreController {
    @Autowired
    GiocatoreService giocatoreService;

    @ApiOperation(value = "Aggiorna ammonizione per un determinato giocatore", response = GiocatoreResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aggiorna ammonizioe", response = GiocatoreResource.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore di server")})
    @PutMapping("/updateAmmonizioni/{idGiocatore}")
    public ResponseEntity<GiocatoreResource> aggiornaAmmonizione(@PathVariable @Min(0) @Max(10000) Integer idGiocatore) {

        return ResponseEntity.ok(giocatoreService.aggiornaAmmonizione(idGiocatore));
    }

}
