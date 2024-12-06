package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.DTO.GiocatoreDTO;
import it.reactive.torneoDemo.DTO.SquadraDTO;
import it.reactive.torneoDemo.DTO.SquadraGiocatoriDTO;
import it.reactive.torneoDemo.resurce.Giocatore;
import it.reactive.torneoDemo.resurce.Squadra;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "squadre", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SquadraController {

    @ApiOperation(value = "Aggiungi una squadra", response = Squadra.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Squadra aggiunta", response = Squadra.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @PostMapping("/salvaSquadra")
    public ResponseEntity<Squadra> salvaSquadra(@RequestBody SquadraDTO squadraDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @ApiOperation(value = "Aggiungi una squadra e una lista giocatori", response = Squadra.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Squadra aggiunta con lista giocatori", response = Squadra.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @PostMapping("/salvaSquadraGiocatori")
    public ResponseEntity<Squadra> salvaSquadraGiocatori(@RequestBody SquadraGiocatoriDTO squadraGiocatoriDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ApiOperation(value = "Ricerca squadre", response = Squadra.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Squadra cercate", response = Squadra.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @GetMapping("/ricercaSquadre/{completo}")
    public ResponseEntity<List<Squadra>> ricercaSquadra(@PathVariable @ApiParam("Parametro che decide se restituire delle squadre vuote o pien") boolean completo) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @ApiOperation(value = "Ricerca squadre", response = Squadra.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Squadra cercate", response = Squadra.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @PutMapping("/aggiungiGiocatore/{idSquadra}")
    public ResponseEntity<Squadra> aggiungiGiocatore(@PathVariable Integer idSquadra, Giocatore giocatore) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
