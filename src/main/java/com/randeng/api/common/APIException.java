package com.randeng.api.common;

public class APIException extends RuntimeException {

    private static final long serialVersionUID = -4521552952257577685L;

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }
}
