package com.bootcamp.ditex3.util;

import org.springframework.http.HttpStatus;

public enum OperationResult {
    OK (HttpStatus.OK),
    NOT_FOUND (HttpStatus.NOT_FOUND),
    EXIST (HttpStatus.CONFLICT);

    private final HttpStatus status;

    OperationResult(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
