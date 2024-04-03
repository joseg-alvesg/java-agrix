package com.betrybe.agrix.ebytr.staff.controllers.dtos;

import com.betrybe.agrix.ebytr.staff.entity.Crops;
import java.time.LocalDate;

/**
 * Crop DTO.
 */
public record CropsDto(
    Long id,
    String name,
    Double plantedArea,
    Long farmId,
    LocalDate plantedDate,
    LocalDate harvestDate) {

  public Crops toEntity() {
    return new Crops(id, name, plantedArea, plantedDate, harvestDate);
  }
}
