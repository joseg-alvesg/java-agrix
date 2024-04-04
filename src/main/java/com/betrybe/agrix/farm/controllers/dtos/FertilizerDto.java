package com.betrybe.agrix.farm.controllers.dtos;

import com.betrybe.agrix.farm.entity.Fertilizers;

/**
 * FertilizerDto.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition) {

  /**
   * Constructor that receives a fertilizer entity.
   *
   * @return Fertilizer entity.
   */
  public Fertilizers toEntity() {
    return new Fertilizers(id, name, brand, composition);
  }
}
