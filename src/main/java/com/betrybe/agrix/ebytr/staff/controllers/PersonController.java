package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dtos.PersonCreationDto;
import com.betrybe.agrix.ebytr.staff.controllers.dtos.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PersonController.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  /**
   * Método construtor.
   *
   * @param personService Serviço de pessoa
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Método que cria uma pessoa.
   *
   * @param personCreationDto dto com as informações da pessoa
   * @return PersonDto com as informações da pessoa criada
   */
  @PostMapping
  public ResponseEntity<PersonDto> createPerson(
      @RequestBody PersonCreationDto personCreationDto) {
    Person person = personService.create(personCreationDto.toEntity());
    return new ResponseEntity<>(
        new PersonDto(person.getId(), person.getUsername(), person.getRole()),
        HttpStatus.CREATED);
  }
}
