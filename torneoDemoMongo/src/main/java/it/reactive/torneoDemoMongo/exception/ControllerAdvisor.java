package it.reactive.torneoDemoMongo.exception;


import it.reactive.torneoDemoMongo.resource.EccezioneResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleException(CustomException e) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("Codice errore", e.getCodErr());
//        body.put("Message", e.getMessage());
        EccezioneResource ex = new EccezioneResource();
        ex.setCod(e.getCodErr());
        ex.setDes(e.getMessaggio());
        return ResponseEntity.status(550).body(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException e) {
        EccezioneResource ex = new EccezioneResource();
        ex.setCod("C6");
        ex.setDes("Errore di validazione");
        //ex.setError(e.getMessage());
        return ResponseEntity.status(550).body(ex);

    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> MethodArgumentNotValidException(MethodArgumentNotValidException ex){
//
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("message", ex.getMessage());
//        body.put("errors", errors);
//        body.put("COD","C6");
//
//        return ResponseEntity.status(550).body(body);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        EccezioneResource e = new EccezioneResource();
        e.setCod("C6");
        e.setDes(errors.toString());
        return ResponseEntity.status(550).body(e);
    }
}
