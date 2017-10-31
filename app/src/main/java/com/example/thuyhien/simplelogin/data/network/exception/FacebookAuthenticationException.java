package com.example.thuyhien.simplelogin.data.network.exception;

/**
 * Created by thuyhien on 10/31/17.
 */

public class FacebookAuthenticationException extends Exception {
    public FacebookAuthenticationException(String message) {
        super(message);
    }

    public FacebookAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
