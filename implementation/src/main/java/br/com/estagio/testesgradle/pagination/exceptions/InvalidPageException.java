package br.com.estagio.testesgradle.pagination.exceptions;

public class InvalidPageException extends RuntimeException {

    public InvalidPageException(String message) {
        super(message);
    }
}
