package com.agrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.agrix.staff.entity.Person;
import com.agrix.staff.repository.PersonRepository;
import com.agrix.staff.security.Role;

/**
 * Seed
 */
@Component
public class Seed implements CommandLineRunner {

  private final PersonRepository personRepository;

  @Autowired
  public Seed(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    seedPerson();
  }

  public void seedPerson() {
    Person admin = new Person();
    admin.setUsername("admin");
    admin.setPassword(new BCryptPasswordEncoder().encode("123"));
    admin.setRole(Role.ADMIN);
    personRepository.save(admin);

    Person manager = new Person();
    manager.setUsername("manager");
    manager.setPassword(new BCryptPasswordEncoder().encode("123"));
    manager.setRole(Role.MANAGER);
    personRepository.save(manager);

    Person user = new Person();
    user.setUsername("user");
    user.setPassword(new BCryptPasswordEncoder().encode("123"));
    user.setRole(Role.USER);
    personRepository.save(user);

  }

}
