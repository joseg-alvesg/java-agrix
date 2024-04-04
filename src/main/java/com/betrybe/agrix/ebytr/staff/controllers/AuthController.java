package com.betrybe.agrix.ebytr.staff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betrybe.agrix.ebytr.staff.controllers.dtos.AuthDto;
import com.betrybe.agrix.ebytr.staff.controllers.dtos.TokenDto;
import com.betrybe.agrix.ebytr.staff.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(
      AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;

  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authDto.username(),
        authDto.password());
    System.out.println("usernamePassword: " + usernamePassword);

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    String token = tokenService.generateToken(auth.getName());

    return new TokenDto(token);
  }
}
