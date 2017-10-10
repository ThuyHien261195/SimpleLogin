package com.example.thuyhien.simplelogin.data.network.exception;

/**
 * Created by thuyhien on 10/10/17.
 */

public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
