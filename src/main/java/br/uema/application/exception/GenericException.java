package br.uema.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class GenericException extends HttpStatusCodeException {
    protected GenericException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
