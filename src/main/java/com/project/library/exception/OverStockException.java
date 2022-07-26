package com.project.library.exception;

public class OverStockException extends RuntimeException{
    public OverStockException() {
        super();
    }

    public OverStockException(String message) {
        super(message);
    }

    public OverStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverStockException(Throwable cause) {
        super(cause);
    }

}
