package com.example.thuyhien.simplelogin.data.network.exception;

/**
 * Created by thuyhien on 11/7/17.
 */

public class LoadProfileException extends Exception {
    public LoadProfileException(String message) {
        super(message);
    }

    public LoadProfileException(String message, Throwable cause) {
        super(message, cause);
    }
}
