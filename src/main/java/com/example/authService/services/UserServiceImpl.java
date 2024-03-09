package com.example.authService.services;

import com.example.authService.exception.InvalidCredentialsException;
import com.example.authService.exception.InvalidTokenException;
import com.example.authService.exception.UserAlreadyExistException;
import com.example.authService.exception.UserNotFoundException;
import com.example.authService.models.Token;
import com.example.authService.models.User;
import com.example.authService.repositories.TokenRepo;
import com.example.authService.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, TokenRepo tokenRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException, InvalidCredentialsException {
        Optional<User> optionalUser = userRepo.findByEmail(email);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with email : " + email);
        }

        if(!bCryptPasswordEncoder.matches(password, optionalUser.get().getPassword())) {
            throw new InvalidCredentialsException("email or password is incorrect");
        }

        Token token = new Token();
        token.setUser(optionalUser.get());
        token.setToken(email +" this is token");
        LocalDate today = LocalDate.now();
        LocalDate oneDayLater = today.plusDays(1);
        token.setExpiryAt(Date.from(oneDayLater.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return token;
    }

    @Override
    public User signup(String email, String password, String name) throws UserAlreadyExistException {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isPresent())  {
            throw new UserAlreadyExistException("User already present with email : " + email);
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(user);
    }

    @Override
    public Token logout(String token) throws InvalidTokenException {
       Optional<Token> optionalToken = tokenRepo.findByToken(token);
       if(optionalToken.isEmpty()) {
           throw new InvalidTokenException(token + "This token in not exist");
       }
       tokenRepo.deleteToken(token);
       return optionalToken.get();
    }
}
