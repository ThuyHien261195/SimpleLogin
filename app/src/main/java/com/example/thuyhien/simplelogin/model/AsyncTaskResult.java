package com.example.thuyhien.simplelogin.model;

/**
 * Created by thuyhien on 10/24/17.
 */

public class AsyncTaskResult<T> {
    private T result;
    private Exception exception;

    public T getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public AsyncTaskResult(T result) {
        this.result = result;
    }

    public AsyncTaskResult(Exception exception) {
        this.exception = exception;
    }
}
