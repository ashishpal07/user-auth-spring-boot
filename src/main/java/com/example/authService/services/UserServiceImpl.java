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
import com.example.authService.repositories.TokenRepo;
import com.example.authService.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public Token login(LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidCredentialsException {
        Optional<User> optionalUser = userRepo.findByEmail(loginRequestDto.getEmail());

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with email : " + loginRequestDto.getEmail());
        }

        if(!loginRequestDto.getPassword().equals(optionalUser.get().getPassword())) {
            throw new InvalidCredentialsException("email or password is incorrect");
        }

        Token token = new Token();
        token.setUser(optionalUser.get());
        token.setToken(loginRequestDto.getEmail()+" this is token");
        return token;
    }

    @Override
    public User signup(SignupRequestDto signupRequestDto) throws UserAlreadyExistException {
        Optional<User> optionalUser = userRepo.findByEmail(signupRequestDto.getEmail());
        if(optionalUser.isPresent())  {
            throw new UserAlreadyExistException(
                    "User already present with email : " + signupRequestDto.getEmail()
            );
        }
        User user = new User();
        user.setEmail(signupRequestDto.getEmail());
        user.setName(signupRequestDto.getName());
        user.setPassword(signupRequestDto.getPassword());
        user.setEmailVerified(false);
        return userRepo.save(user);
    }

    @Override
    public Token logout(LogoutRequestDto logoutRequestDto) throws InvalidTokenException {
       Optional<Token> optionalToken = tokenRepo.findByToken(logoutRequestDto.getToken());
       if(optionalToken.isEmpty()) {
           throw new InvalidTokenException(logoutRequestDto.getToken() + "This token in not exist");
       }
       tokenRepo.deleteToken(logoutRequestDto.getToken());
       return optionalToken.get();
    }
}
