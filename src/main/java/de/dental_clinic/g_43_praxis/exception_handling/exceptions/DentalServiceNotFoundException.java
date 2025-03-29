package de.dental_clinic.g_43_praxis.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DentalServiceNotFoundException extends RuntimeException {
    public DentalServiceNotFoundException(String message) {super(message);}
}
