package com.example.lottery.controller;

import com.example.lottery.ballot.exceptions.BallotException;
import com.example.lottery.users.exceptions.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralExceptionHandler {

    @ExceptionHandler(exception = BallotException.class)
    public ResponseEntity<String> handleBallotException(BallotException ballotException) {
        return ResponseEntity
                .status(ballotException.getStatus().value())
                .body(ballotException.getMessage());
    }

    @ExceptionHandler(exception = UserException.class)
    public ResponseEntity<String> handleUserException(UserException userException) {
        return ResponseEntity
                .status(userException.getStatus().value())
                .body(userException.getMessage());
    }

}
