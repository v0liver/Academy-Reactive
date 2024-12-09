package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.resource.TorneoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "tornei", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TorneoController {


    @ApiOperation(value = "Creo un nuovo torneo", response = TorneoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Torneo creato con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @PostMapping
    public ResponseEntity<TorneoResponse> aggiungiTorneo(@RequestBody @Valid TorneoDTO torneoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @ApiOperation(value = "Censisco una squadra al torneo", response = TorneoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sqadra aggiunta al torneo con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @PutMapping("/addSquadraToTorneo/{idTorneo}/{idSquadra}")
    public ResponseEntity<TorneoResponse> censitaSquadraAlTorneo(@PathVariable Integer idTorneo, @PathVariable Integer idSquadra){
        return ResponseEntity.ok(null);
    }


    @ApiOperation(value = "Ritorna tutti i tornei con tutte le squadre", response = TorneoResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tornei e squadre recuperato con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @GetMapping()
    public ResponseEntity<List<TorneoResponse>> getTorneoEndSquadre(){
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "Elimino il torneo con relative squadre assciare se non fanno parte di una altro torneo con relativi giocatori", response = TorneoResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tornei,squadre e giocatori eliminati con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @DeleteMapping("/{idTorneo}")
    public ResponseEntity<List<TorneoResponse>> eliminaTorneoConSquadreAndGiocatori(@PathVariable Integer idTorneo){
        return ResponseEntity.ok(null);
    }

}
