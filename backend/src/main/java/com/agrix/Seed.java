package com.agrix;

import com.agrix.farm.entity.Crops;
import com.agrix.farm.entity.Farm;
import com.agrix.farm.repository.CropsRepository;
import com.agrix.farm.repository.FarmRepository;
import com.agrix.staff.entity.Person;
import com.agrix.staff.repository.PersonRepository;
import com.agrix.staff.security.Role;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seed.
 */
@Component
public class Seed implements CommandLineRunner {

  private final PersonRepository personRepository;
  private final FarmRepository farmRepository;
  private final CropsRepository cropRepository;

  @Autowired
  public Seed(
      PersonRepository personRepository,
      FarmRepository farmRepository,
      CropsRepository cropRepository) {
    this.personRepository = personRepository;
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;

  }

  @Override
  public void run(String... args) throws Exception {
    seedPerson();
    seedFarm();
    seedCrops();
  }

  /**
   * Método para criar pessoas.
   */
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

  public void seedFarm() {
    Farm farm1 = new Farm();
    farm1.setName("Farm 1");
    farm1.setSize(10.0);
    farmRepository.save(farm1);

    Farm farm2 = new Farm();
    farm2.setName("Farm 2");
    farm2.setSize(20.0);
    farmRepository.save(farm2);
  }

  public void seedCrops() {
    Crops crop1 = new Crops();
    crop1.setName("Crop 1");
    crop1.setPlantedArea(5.0);
    crop1.setFarm(farmRepository.findById(1L).get());
    crop1.setPlantedDate(LocalDate.now());
    crop1.setHarvestDate(LocalDate.now().plusYears(3));
    cropRepository.save(crop1);
  }
}
