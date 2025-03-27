package de.dental_clinic.g_43_praxis.exception_handling.exceptions;

public class AppointmentValidationException extends RuntimeException{
    public AppointmentValidationException(String message) {
        super(message);
    }
}
