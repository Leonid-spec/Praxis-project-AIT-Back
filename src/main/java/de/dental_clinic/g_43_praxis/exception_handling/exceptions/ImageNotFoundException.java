package de.dental_clinic.g_43_praxis.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(Long id) {
        super("Image with ID " + id + " not found");
    }
}