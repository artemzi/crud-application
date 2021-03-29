package com.artemzi.cores.exceptions;

public class CoreException extends RuntimeException {

    public CoreException() {
        this("Core exception");
    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
