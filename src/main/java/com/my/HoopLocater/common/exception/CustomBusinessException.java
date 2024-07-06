package com.my.HoopLocater.common.exception;

public class CustomBusinessException extends RuntimeException{

    public CustomBusinessException() {
        super();
    }

    public CustomBusinessException(String message) {
        super(message);
    }

    public CustomBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomBusinessException(Throwable cause) {
        super(cause);
    }
}
