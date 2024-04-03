package com.betrybe.agrix.ebytr.staff.controllers.dtos;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonCreationDto
 */
public record PersonCreationDto(String username, String password, Role role) {
  public Person toEntity() {
    return new Person(username, password, role);
  }
}
