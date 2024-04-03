package com.betrybe.agrix.ebytr.staff.controllers.dtos;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonDto
 */
public record PersonDto(
    Long id,
    String username,
    Role role) {

  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole());
  }
}
