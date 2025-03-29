package de.dental_clinic.g_43_praxis.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class FailedDependencyException extends RuntimeException {
    public FailedDependencyException(String message) {
        super(message);
    }
}