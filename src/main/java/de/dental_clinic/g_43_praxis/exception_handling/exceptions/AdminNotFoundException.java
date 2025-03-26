package de.dental_clinic.g_43_praxis.exception_handling.exceptions;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}
