package com.agrix.auth.controllers;

import com.agrix.auth.controllers.dtos.AuthDto;
import com.agrix.auth.controllers.dtos.TokenDto;
import com.agrix.staff.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Authentication")
public class AuthController {

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  /**
   * Método construtor.
   * jdbc:postgresql://${DB_HOSTNAME}:${DB_PORT}/${DB_NAME}
   *
   * @param authenticationManager Classe que realiza a autenticação.
   * @param tokenService          Serviço de token.
   */
  @Autowired
  public AuthController(
      AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;

  }

  /**
   * Método que realiza o login.
   *
   * @param authDto dto com as informações de autenticação.
   * @return TokenDto com o token gerado.
   */
  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
        authDto.username(),
        authDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    String token = tokenService.generateToken(auth.getName());

    return new TokenDto(token);
  }

  @PostMapping("/token")
  public String validateToken(@RequestBody TokenDto tokenDto) {
    return tokenService.validateToken(tokenDto.token());
  }
}
