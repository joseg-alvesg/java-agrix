package com.betrybe.agrix.farm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Fertilizers entity.
 */
@Entity
@Table(name = "fertilizers")
public class Fertilizers {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String brand;
  private String composition;

  @ManyToMany
  private List<Crops> crops;

  public Fertilizers() {
  }

  /**
   * Constructor that receives a farm entity.
   *
   * @param name        Fertilizer name.
   * @param brand       Fertilizer brand.
   * @param composition Fertilizer composition.
   */
  public Fertilizers(String name, String brand, String composition) {
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  /**
   * Constructor that receives a farm entity.
   *
   * @param id          Fertilizer id.
   * @param name        Fertilizer name.
   * @param brand       Fertilizer brand.
   * @param composition Fertilizer composition.
   */
  public Fertilizers(Long id, String name, String brand, String composition) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Crops> getCrops() {
    return crops;
  }

  public void setCrops(List<Crops> crops) {
    this.crops = crops;
  }
}
