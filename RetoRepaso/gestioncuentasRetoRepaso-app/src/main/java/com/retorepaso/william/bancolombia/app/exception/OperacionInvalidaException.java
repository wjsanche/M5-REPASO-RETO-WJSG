package com.retorepaso.william.bancolombia.app.exception;


public class OperacionInvalidaException extends RuntimeException {
    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}