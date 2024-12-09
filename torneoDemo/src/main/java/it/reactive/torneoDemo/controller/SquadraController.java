package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.squadra.SquadraGiocatoreDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.eccezioni.CustomException;
import it.reactive.torneoDemo.resource.SquadraResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "squadre", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SquadraController {

    @ApiOperation(value = "Creao una nuova squadra", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Squadra creata con successo"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server"),
            @ApiResponse(code = 550, message = "Squadra duplicata", response = CustomException.class)
    })
    @PostMapping
    public ResponseEntity<SquadraResponse> salvaSquadra(@RequestBody @Valid SquadraDTO squadraDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @ApiOperation(value = "Creao una nuova squadra con la lista di giocatore", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Squadra e giocatori creata con successo", response = SquadraResponse.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server"),
            @ApiResponse(code = 550, message = "La squadra gia censita", response = CustomException.class)
    })
    @PostMapping("/squadregiocatori")
    public ResponseEntity<SquadraResponse> salvaSquadraSquadraGiocatori(@RequestBody @Valid SquadraGiocatoreDTO squadraGiocatoreDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @ApiOperation(value = "Ricerca squadra con lista giocatori", response = SquadraResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @GetMapping("/ricercaSquadre/completo")
    public ResponseEntity<List<SquadraResponse>> ricercaSquadra(@RequestParam @ApiParam("Parametro che mi inizializza una lista di giocatori vuota o meno") boolean completo) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "Aggiungo una giocatore ad una determinata squadra", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Squadre cercate", response = SquadraResponse.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @PutMapping("/addGiocatore/{id}")
    public ResponseEntity<SquadraResponse> aggiungiGiocatore(@PathVariable @ApiParam(value = "id squadra", required = true) Integer id, @Valid @RequestBody @ApiParam(value = "giocatoreDTO", required = true) GiocatoreDto giocatoreDTO) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "Aggiorno una tifoseria se no ne creo una", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tifoseria aggiornata o creata con sucesso", response = SquadraResponse.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @PutMapping("/addTifoseria/{id}")
    public ResponseEntity<SquadraResponse> aggiungiTifoseria(@PathVariable @ApiParam(value = "id squadra", required = true) Long id, @RequestBody @ApiParam(value = "tifoseria") TifoseriaDTO tifoseriaDTO) {
        return ResponseEntity.ok(null);
    }


    @ApiOperation(value = "Elimino squadra con relativi giocatori", response = SquadraResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Squadra eliminata con sucesso", response = SquadraResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @DeleteMapping("/eliminaSquadra/{idSquadra}")
    public ResponseEntity<Void> rimuoviSquadra(@PathVariable Integer idSquadra) {
        return ResponseEntity.noContent().build();
    }

    //TODO vedere il tipo di ritorno

}
