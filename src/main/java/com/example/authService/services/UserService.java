package com.example.authService.services;

import com.example.authService.dtos.LoginRequestDto;
import com.example.authService.dtos.LogoutRequestDto;
import com.example.authService.dtos.SignupRequestDto;
import com.example.authService.exception.InvalidCredentialsException;
import com.example.authService.exception.InvalidTokenException;
import com.example.authService.exception.UserAlreadyExistException;
import com.example.authService.exception.UserNotFoundException;
import com.example.authService.models.Token;
import com.example.authService.models.User;

public interface UserService {
    public Token login(LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidCredentialsException;

    public User signup(SignupRequestDto signupRequestDto) throws UserAlreadyExistException;

    public Token logout(LogoutRequestDto logoutRequestDto) throws InvalidTokenException;
}
