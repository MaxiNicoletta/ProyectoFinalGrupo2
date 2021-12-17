package com.example.DesafioSprint.Exceptions;

import com.example.DesafioSprint.DTOs.ErrorDTO;
import com.example.DesafioSprint.DTOs.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UbicacionException.class)
    public ResponseEntity<StatusDTO> lanzarExcepcionUbicacion(UbicacionException ex) {
        StatusDTO err = new StatusDTO();
        err.setCode(ex.getCODE().value());
        err.setMessage(ex.getERROR());
        return new ResponseEntity<>(err, ex.getCODE());
    }

    @ExceptionHandler(FaltanParametros.class)
    public ResponseEntity<StatusDTO> lanzarExcepcionFaltanParametros(FaltanParametros ex) {
        StatusDTO err = new StatusDTO();
        err.setCode(ex.getCODE().value());
        err.setMessage(ex.getERROR());
        return new ResponseEntity<>(err, ex.getCODE());
    }

    @ExceptionHandler(HotelesException.class)
    public ResponseEntity<StatusDTO> lanzarExceptionHoteles(HotelesException ex) {
        StatusDTO err = new StatusDTO();
        err.setCode(ex.getCODE().value());
        err.setMessage(ex.getERROR());
        return new ResponseEntity<>(err, ex.getCODE());
    }

    @ExceptionHandler(PersonasException.class)
    public ResponseEntity<StatusDTO> lanzarExceptionPersonas(PersonasException ex) {
        StatusDTO err = new StatusDTO();
        err.setCode(ex.getCODE().value());
        err.setMessage(ex.getERROR());
        return new ResponseEntity<>(err, ex.getCODE());
    }

    @ExceptionHandler(VuelosException.class)
    public ResponseEntity<StatusDTO> lanzarExceptionVuelos(VuelosException ex) {
        StatusDTO err = new StatusDTO();
        err.setCode(ex.getCODE().value());
        err.setMessage(ex.getERROR());
        return new ResponseEntity<>(err, ex.getCODE());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> noValida(MethodArgumentNotValidException ex) {
        ErrorDTO err = new ErrorDTO();
        err.setNombre("DTO Erroneo");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getRejectedValue().toString();//String fieldName = ((FieldError) error).getFieldError().getDefaultMessage());
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        err.setDescripcion(errors);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }


}
