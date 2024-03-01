package com.example.authService.controllers;

import com.example.authService.dtos.LoginRequestDto;
import com.example.authService.dtos.LogoutRequestDto;
import com.example.authService.dtos.SignupRequestDto;
import com.example.authService.exception.InvalidCredentialsException;
import com.example.authService.exception.InvalidTokenException;
import com.example.authService.exception.UserAlreadyExistException;
import com.example.authService.exception.UserNotFoundException;
import com.example.authService.models.Token;
import com.example.authService.models.User;
import com.example.authService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public User signup(
            @RequestBody SignupRequestDto signupRequestDto
    ) throws UserAlreadyExistException {
        return userService.signup(signupRequestDto);
    }

    @GetMapping("/login")
    public Token login(
            @RequestBody LoginRequestDto loginRequestDto
    ) throws UserNotFoundException, InvalidCredentialsException {
        return userService.login(loginRequestDto);
    }

    @DeleteMapping("/logout")
    public Token logout(
            @RequestBody LogoutRequestDto logoutRequestDto
    ) throws InvalidTokenException {
        return userService.logout(logoutRequestDto);
    }

}
