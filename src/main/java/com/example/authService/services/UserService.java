package com.example.authService.services;

import com.example.authService.exception.InvalidCredentialsException;
import com.example.authService.exception.InvalidTokenException;
import com.example.authService.exception.UserAlreadyExistException;
import com.example.authService.exception.UserNotFoundException;
import com.example.authService.models.Token;
import com.example.authService.models.User;

public interface UserService {
    Token login(String email, String password) throws UserNotFoundException, InvalidCredentialsException;

    User signup(String email, String password, String name) throws UserAlreadyExistException;

    Token logout(String token) throws InvalidTokenException;
}
