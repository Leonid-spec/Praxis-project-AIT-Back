package de.dental_clinic.g_43_praxis.exception_handling;

import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DentalServiceValidationException.class)
    public ResponseEntity<String> handleDentalServiceValidationException(DentalServiceValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}