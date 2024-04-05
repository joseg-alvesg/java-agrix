package com.agrix.farm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Farm entity.
 */
@Entity
@Table(name = "farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Double size;

  @OneToMany(mappedBy = "farm")
  @JsonIgnore
  private List<Crops> crops;

  public Farm() {
  }

  /**
   * Constructor that receives a farm entity.
   *
   * @param id   Farm id.
   * @param name Farm name.
   * @param size Farm size.
   */
  public Farm(Long id, String name, double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  /**
   * Constructor that receives a farm entity.
   *
   * @param id    Farm id.
   * @param name  Farm name.
   * @param size  Farm size.
   * @param crops Crops list.
   */
  public Farm(Long id, String name, double size, List<Crops> crops) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.crops = crops;
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

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public List<Crops> getCrops() {
    return crops;
  }

  public void setCrops(Crops crops) {
    this.crops.add(crops);
  }

}
