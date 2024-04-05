package com.agrix.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TokenService.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  /**
   * Método construtor.
   *
   * @param secret Secret para geração do token.
   */
  @Autowired
  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Método que gera um token.
   *
   * @param username Usuário para o qual o token será gerado.
   * @return Token gerado.
   */
  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return Instant.now().plus(8, ChronoUnit.HOURS);
  }

  /**
   * Método que valida um token.
   *
   * @param token Token a ser validado.
   * @return Usuário do token.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }
}
