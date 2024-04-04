package com.betrybe.agrix.ebytr.staff.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

/**
 * Crops entity.
 */
@Entity
@Table(name = "crops")
@Secured({ "MANAGER", "ADMIN" })
public class Crops {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Double plantedArea;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  private LocalDate plantedDate;
  private LocalDate harvestDate;

  @ManyToMany
  @JsonIgnore
  List<Fertilizers> fertilizers;

  public Crops() {
  }

  /**
   * Constructor that receives a farm entity.
   *
   * @param id          Crop id.
   * @param name        Crop name.
   * @param plantedArea Crop planted area.
   */
  public Crops(
      Long id,
      String name,
      Double plantedArea) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
  }

  /**
   * Constructor that receives a farm entity.
   *
   * @param id          Crop id.
   * @param name        Crop name.
   * @param plantedArea Crop planted area.
   * @param plantedDate Crop planted date.
   * @param harvestDate Crop harvest date.
   */
  public Crops(
      Long id,
      String name,
      Double plantedArea,
      LocalDate plantedDate,
      LocalDate harvestDate) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;

  }

  /**
   * Constructor that receives a farm entity.
   *
   * @param id          Crop id.
   * @param name        Crop name.
   * @param plantedArea Crop planted area.
   * @param farm        Farm entity.
   */
  public Crops(
      Long id, String name, Double plantedArea, Farm farm) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.farm = farm;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public Farm getFarm() {
    return farm;
  }

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizers> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizers> fertilizers) {
    this.fertilizers = fertilizers;
  }
}
