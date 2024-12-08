package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.DTO.TorneoDTO;
import it.reactive.torneoDemo.resurce.Torneo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "torneo", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TorneoController {
    @ApiOperation(value = "Crea un torneo", response = Torneo.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "torneo aggiunto", response = Torneo.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @PostMapping("/aggiungiTorneo")
    public ResponseEntity<Torneo> aggiungiTorneo(@RequestBody TorneoDTO torneoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ApiOperation(value = "censisci squadra al torneo", response = Torneo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Squadra aggiunta al torneo", response = Torneo.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @PutMapping("/censisciSquadra/{idSquadra}/{idTorneo}")
    public ResponseEntity<Torneo> censisciSquadra(@PathVariable Integer idSquadra,@PathVariable Integer idTorneo) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(value = "Ricerca torneo", response = Torneo.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tornei e squadre recuperate", response = Torneo.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @GetMapping("/ricercaTorneo")
    public ResponseEntity<List<Torneo>> ricercaTorneo() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(value = "Elimino squadra e giocatori", response = Torneo.class, responseContainer =
            "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Torneo e squadre eliminate", response = Torneo.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore server")
    })
    @DeleteMapping("/eliminaTorneo/{idTorneo}")
    public ResponseEntity<List<Torneo>> eliminaTorneo(@PathVariable Integer idTorneo) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
