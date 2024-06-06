package com.my.HoopLocater.common.exception;

public class CustomHoopException extends RuntimeException{

    public CustomHoopException() {
        super();
    }

    public CustomHoopException(String message) {
        super(message);
    }

    public CustomHoopException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomHoopException(Throwable cause) {
        super(cause);
    }
}
