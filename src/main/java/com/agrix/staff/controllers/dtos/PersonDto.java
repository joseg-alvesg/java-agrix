package com.agrix.staff.controllers.dtos;

import com.agrix.staff.entity.Person;
import com.agrix.staff.security.Role;

/**
 * PersonDto.
 */
public record PersonDto(
    Long id,
    String username,
    Role role) {

  /**
   * Método que converte um objeto Person em um PersonDto.
   *
   * @param person pessoa a ser convertida.
   * @return PersonDto convertido.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole());
  }
}
