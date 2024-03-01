package com.example.authService.controllers.advices;

import com.example.authService.dtos.ExceptionDto;
import com.example.authService.exception.InvalidCredentialsException;
import com.example.authService.exception.InvalidTokenException;
import com.example.authService.exception.UserAlreadyExistException;
import com.example.authService.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserAlreadyExistException.class)
    private ResponseEntity<ExceptionDto> handleUserAlreadyExistException(UserAlreadyExistException e) {
        ExceptionDto exception = new ExceptionDto();
        exception.setMessage(e.getMessage());
        exception.setStatus("Failure");
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionDto exception = new ExceptionDto();
        exception.setMessage(e.getMessage());
        exception.setStatus("Failure");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    private ResponseEntity<ExceptionDto> handleInvalidCredentialsException(InvalidCredentialsException e) {
        ExceptionDto exception = new ExceptionDto();
        exception.setMessage(e.getMessage());
        exception.setStatus("Failure");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<ExceptionDto> handleInvalidTokenException(InvalidTokenException e) {
        ExceptionDto exception = new ExceptionDto();
        exception.setMessage(e.getMessage());
        exception.setStatus("Failure");
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
