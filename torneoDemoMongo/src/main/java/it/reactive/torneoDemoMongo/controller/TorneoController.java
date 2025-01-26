package it.reactive.torneoDemoMongo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemoMongo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemoMongo.resource.TorneoResource;
import it.reactive.torneoDemoMongo.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "tornei", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class TorneoController {
    @Autowired
    TorneoService torneoService;


    @ApiOperation(value = "Creo un nuovo torneo", response = TorneoResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Torneo creato con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @PostMapping
    public ResponseEntity<TorneoResource> aggiungiTorneo(@RequestBody @Valid TorneoDTO torneoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoService.addTorneo(torneoDTO));
    }


    @ApiOperation(value = "Censisco una squadra al torneo", response = TorneoResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sqadra aggiunta al torneo con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @PutMapping("/addSquadraToTorneo/{nomeTorneo}/{nomeSquadra}")
    public ResponseEntity<TorneoResource> censitaSquadraAlTorneo(String nomeTorneo,
                                                                 String nomeSquadra) {
        return ResponseEntity.ok(torneoService.censitaSquadraTorneo(nomeTorneo, nomeSquadra));
    }


    @ApiOperation(value = "Ritorna tutti i tornei con tutte le squadre", response = TorneoResource.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tornei e squadre recuperato con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @GetMapping()
    public ResponseEntity<List<TorneoResource>> getTorneoEndSquadre() {
        return ResponseEntity.ok(torneoService.getTorneoAndSquadre());
    }

    @ApiOperation(value = "Elimino il torneo con relative squadre assciare se non fanno parte di una altro torneo con relativi giocatori", response = TorneoResource.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tornei,squadre e giocatori eliminati con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @DeleteMapping("/{nomeTorneo}")
    public ResponseEntity<List<TorneoResource>> eliminaTorneoConSquadreAndGiocatori(@PathVariable String nomeTorneo) {
        return ResponseEntity.ok(torneoService.removeTorneoAndSquadre(nomeTorneo));
    }

}
