package com.agrix.auth.security;

import com.agrix.staff.service.PersonService;
import com.agrix.staff.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JwtFilter.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final PersonService personService;

  /**
   * Método construtor.
   *
   * @param tokenService  Serviço de token
   * @param personService Serviço de pessoa
   */
  @Autowired
  public JwtFilter(TokenService tokenService, PersonService personService) {
    this.tokenService = tokenService;
    this.personService = personService;
  }

  private Optional<String> extractToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");

    if (header == null) {
      return Optional.empty();
    }
    return Optional.of(
        header.replace("Bearer ", ""));
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    Optional<String> token = extractToken(request);

    if (token.isPresent()) {

      String sub = tokenService.validateToken(token.get());

      UserDetails userDetails = personService.loadUserByUsername(sub);

      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);
  }

}
