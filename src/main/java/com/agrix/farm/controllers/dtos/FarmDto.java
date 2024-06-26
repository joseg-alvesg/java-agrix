package com.agrix.farm.controllers.dtos;

import com.agrix.farm.entity.Farm;

/**
 * Farm DTO.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Constructor that receives a farm entity.
   *
   * @return Farm entity.
   */
  public Farm toEntity() {
    return new Farm(id, name, size);
  }

}
