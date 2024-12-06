package it.reactive.torneoDemo.exception;

import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SquadraDuplicataException.class)
    public ResponseEntity<Object> handlerSquadraDuplicataException(SquadraDuplicataException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        body.put("timestamp", formatDateTime);
        body.put("COD","C1");
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(GiocatoreDuplicataException.class)
    public ResponseEntity<Object> handlerGiocatoreDuplicataException(SquadraDuplicataException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        body.put("timestamp", formatDateTime);
        body.put("COD","C3");
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(SquadraNonPresenteExceptionException.class)
    public ResponseEntity<Object> handlerSquadraNonPresenteException(SquadraDuplicataException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        body.put("timestamp", formatDateTime);
        body.put("COD","C4");
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
