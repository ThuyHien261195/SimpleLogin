package com.example.thuyhien.simplelogin.ui.exception;

/**
 * Created by thuyhien on 10/10/17.
 */

public class InvalidInputException extends Exception {
    public static final String ERROR_CODE_EMPTY_EMAIL = "EmptyEmail";
    public static final String ERROR_CODE_INVALID_EMAIL = "InvalidEmail";
    public static final String ERROR_CODE_EMPTY_PASSWORD = "EmptyPassword";
    public static final String ERROR_CODE_INVALID_PASSWORD = "InvalidPassword";

    private String errorCode;

    public InvalidInputException(String errorCode) {
        this.errorCode = errorCode;
    }

    public InvalidInputException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvalidInputException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
