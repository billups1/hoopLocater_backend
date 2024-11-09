package com.my.HoopLocater.common.exception;

public class CustomAuthException extends RuntimeException{

    public CustomAuthException() {
        super();
    }

    public CustomAuthException(String message) {
        super(message);
    }

    public CustomAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomAuthException(Throwable cause) {
        super(cause);
    }
}
