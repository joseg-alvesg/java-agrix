package com.agrix.farm.controllers.dtos;

import com.agrix.farm.entity.Crops;
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

  /**
   * Método que retorna uma entidade de cultivo.
   *
   * @return Entidade de cultivo.
   */
  public Crops toEntity() {
    return new Crops(
        id,
        name,
        plantedArea,
        plantedDate,
        harvestDate);
  }
}
