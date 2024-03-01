package com.example.authService.repositories;

import com.example.authService.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    @Query("select t from Token t where token = :token")
    Optional<Token> findByToken(String token);

    @Modifying
    @Query("delete from Token t where t.token = :token")
    void deleteToken(@Param("token") String token);
}
