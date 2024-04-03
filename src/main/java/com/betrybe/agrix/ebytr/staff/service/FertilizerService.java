package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizers;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FertilizerService.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Fertilizers> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  public Fertilizers insertFertilizer(Fertilizers fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public Optional<Fertilizers> getFertilizerById(Long id) {
    return fertilizerRepository.findById(id);
  }
}
