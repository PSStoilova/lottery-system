package com.example.lottery.ballot.exceptions;

import org.springframework.http.HttpStatus;

public class BallotException extends RuntimeException {
    private final HttpStatus status;

    public BallotException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
