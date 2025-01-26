package it.reactive.torneoDemoMongo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemoMongo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemoMongo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemoMongo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemoMongo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemoMongo.resource.EccezioneResource;
import it.reactive.torneoDemoMongo.resource.SquadraResource;
import it.reactive.torneoDemoMongo.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "squadre", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class SquadraController {
    @Autowired
    SquadraService squadraService;

    @ApiOperation(value = "Creao una nuova squadra", response = SquadraResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Squadra creata con successo"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server"),
            @ApiResponse(code = 550, message = "Squadra duplicata", response = EccezioneResource.class)
    })
    @PostMapping
    public ResponseEntity<SquadraResource> salvaSquadra(@RequestBody @Valid SquadraDTO squadraDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(squadraService.salvaSquadra(squadraDTO));
    }


    @ApiOperation(value = "Creao una nuova squadra con la lista di giocatore", response = SquadraResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Squadra e giocatori creata con successo", response = SquadraResource.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            //@ApiResponse(code = 500, message = "Errore del server"),
            @ApiResponse(code = 550, message = "\t\n" +
                    "Il servizio va in errore con i cod:\n" +
                    "\n" +
                    "• C1 in caso di squadra già censita\n" +
                    "• C6 in caso di errore di validazione", response = EccezioneResource.class)
    })
    @PostMapping("/squadreGiocatori")
    public ResponseEntity<SquadraResource> salvaSquadraSquadraGiocatori(@RequestBody @Valid SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(squadraService.salvaSquadraCongiocatori(squadreDiGiocatoriDTO));
    }


    @ApiOperation(value = "Ricerca squadra con lista giocatori", response = SquadraResource.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @GetMapping
    public ResponseEntity<List<SquadraResource>> ricercaSquadra(@RequestParam @ApiParam("Parametro che mi inizializza una lista di giocatori vuota o meno") boolean completo) {
        return ResponseEntity.ok(squadraService.ricercaSquadre(completo));
    }

    @ApiOperation(value = "Aggiungo una giocatore ad una determinata squadra", response = SquadraResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Squadre cercate", response = SquadraResource.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @PutMapping("/addGiocatore/{nomeSquadra}")
    public ResponseEntity<SquadraResource> aggiungiGiocatore(@PathVariable @ApiParam(value = "nome squadra", required =
            true) String nomeSquadra,
                                                             @Valid @RequestBody @ApiParam(value = "giocatoreDTO", required = true) GiocatoreDto giocatoreDTO) {
        return ResponseEntity.ok(squadraService.aggiungiGiocatore(giocatoreDTO, nomeSquadra));
    }

    @ApiOperation(value = "Aggiorno una tifoseria se no ne creo una", response = SquadraResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tifoseria aggiornata o creata con sucesso", response = SquadraResource.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @PutMapping("/addTifoseria/{nomeSquadra}")
    public ResponseEntity<SquadraResource> aggiungiTifoseria(@PathVariable @ApiParam(value = "nomeSquadra", required =
            true) String nomeSquadra, @RequestBody @ApiParam(value = "tifoseria") @Valid TifoseriaDTO tifoseriaDTO) {
        return ResponseEntity.ok(squadraService.addTifoseria(tifoseriaDTO, nomeSquadra));
    }


    @ApiOperation(value = "Elimino squadra con relativi giocatori", response = SquadraResource.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Squadra eliminata con sucesso", response = SquadraResource.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @DeleteMapping("/{nomeSquadra}")
    public ResponseEntity<Void> rimuoviSquadra(@PathVariable String nomeSquadra) {
        squadraService.deleteSquadra(nomeSquadra);
        return ResponseEntity.noContent().build();
    }


}
