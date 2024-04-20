package com.myComp.parkingApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptonalHandler {

    @ExceptionHandler(DataBaseEmpty.class)
    public ResponseEntity<?> dataBaseEmpty(DataBaseEmpty ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(VehicalNotFound.class)
    public ResponseEntity<?> vehicalNotFound(VehicalNotFound ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(VehicalExist.class)
    public ResponseEntity<?> vehicalExist(VehicalExist ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(VehicalCannotUpdat.class)
    public  ResponseEntity<?> vehicalCannotUpdat(VehicalCannotUpdat ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DeletionError.class)
    public  ResponseEntity<?> deletionError(DeletionError ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @SuppressWarnings("null")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptio(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
