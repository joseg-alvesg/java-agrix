package com.agrix.staff.controllers.dtos;

import com.agrix.staff.entity.Person;
import com.agrix.staff.security.Role;

/**
 * PersonCreationDto.
 */
public record PersonCreationDto(String username, String password, Role role) {
  /**
   * Método que converte um PersonCreationDto em um Person.
   *
   * @return Person convertido.
   */
  public Person toEntity() {
    return new Person(username, password, role);
  }
}
