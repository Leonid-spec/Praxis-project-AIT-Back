package de.dental_clinic.g_43_praxis.exception_handling.exceptions;

public class DoctorAlreadyExistsException extends RuntimeException {
    public DoctorAlreadyExistsException(String message) {
        super(message);
    }
}
