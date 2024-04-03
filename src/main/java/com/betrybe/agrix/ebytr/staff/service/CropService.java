package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Crops;
import com.betrybe.agrix.ebytr.staff.repository.CropsRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {

  private final CropsRepository cropsRepository;

  @Autowired
  public CropService(CropsRepository cropsRepository) {
    this.cropsRepository = cropsRepository;
  }

  public List<Crops> getAllCrops() {
    return cropsRepository.findAll();
  }

  public Optional<Crops> getCropById(Long id) {
    return cropsRepository.findById(id);
  }

  public List<Crops> getCropsByPlantedDate(LocalDate start, LocalDate end) {
    return cropsRepository.findByPlantedDateBetween(start, end);
  }

  public List<Crops> getCropsByHarvestDate(LocalDate start, LocalDate end) {
    return cropsRepository.findByHarvestDateBetween(start, end);
  }

  public Optional<Crops> insertCrop(Crops crop) {
    return Optional.of(cropsRepository.save(crop));
  }
}
