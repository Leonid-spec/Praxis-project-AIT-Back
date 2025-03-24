package de.dental_clinic.g_43_praxis.exception_handling.exceptions;

public class DentalServiceAlreadyExistsException extends RuntimeException {
    public DentalServiceAlreadyExistsException(String message) {
        super(message);
    }
}
