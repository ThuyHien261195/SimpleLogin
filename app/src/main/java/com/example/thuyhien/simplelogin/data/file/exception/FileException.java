package com.example.thuyhien.simplelogin.data.file.exception;

/**
 * Created by thuyhien on 10/19/17.
 */

public class FileException extends Exception {
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
