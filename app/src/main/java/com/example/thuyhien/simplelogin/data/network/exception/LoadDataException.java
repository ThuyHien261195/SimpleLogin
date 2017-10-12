package com.example.thuyhien.simplelogin.data.network.exception;

/**
 * Created by thuyhien on 10/12/17.
 */

public class LoadDataException extends Exception {

    public LoadDataException(String message) {
        super(message);
    }

    public LoadDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
