package com.betrybe.agrix.ebytr.staff.controllers.dtos;

import com.betrybe.agrix.ebytr.staff.entity.Farm;

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
