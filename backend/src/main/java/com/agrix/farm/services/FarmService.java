package com.agrix.farm.services;

import com.agrix.farm.entity.Crops;
import com.agrix.farm.entity.Farm;
import com.agrix.farm.repository.CropsRepository;
import com.agrix.farm.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Farm service.
 */
@Service
public class FarmService {

  /**
   * Farm repository.
   * Crops repository.
   */
  private final FarmRepository farmRepository;
  private final CropsRepository cropsRepository;

  /**
   * Constructor that receives a farm repository.
   *
   * @param farmRepository Farm repository.
   */
  @Autowired
  public FarmService(FarmRepository farmRepository,
      CropsRepository cropsRepository) {
    this.farmRepository = farmRepository;
    this.cropsRepository = cropsRepository;
  }

  public List<Farm> getAllFarm() {
    return farmRepository.findAll();
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Método que retorna uma fazenda pelo seu id.
   *
   * @param id id da fazenda
   * @return Optional com a fazenda ou um Optional vazio.
   */
  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }

  /**
   * Método que retorna todas as plantações de uma fazenda.
   *
   * @param farmId id da fazenda
   * @return Optional com todas as plantações da fazenda ou um Optional vazio.
   */
  public Optional<Crops> insertCrops(Long farmId, Crops crops) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);
    if (optionalFarm.isPresent()) {
      Farm farm = optionalFarm.get();
      crops.setFarm(farm);
      farm.setCrops(crops);
      Crops savedCrops = cropsRepository.save(crops);
      return Optional.of(savedCrops);
    }
    return Optional.empty();
  }

  /**
   * Método que retorna todas as plantações de uma fazenda.
   *
   * @param id id da fazenda
   * @return Optional com todas as plantações da fazenda ou um Optional vazio.
   */
  public Optional<Crops> getCropsById(Long id) {
    return cropsRepository.findById(id);
  }

  /**
   * Método que retorna todas as plantações de uma fazenda.
   *
   * @param farmId id da fazenda
   * @return Optional com todas as plantações da fazenda ou um Optional vazio.
   */
  public Optional<List<Crops>> getAllCropsByFarmId(Long farmId) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);
    if (optionalFarm.isPresent()) {
      Farm farm = optionalFarm.get();
      List<Crops> crops = farm.getCrops();
      return Optional.of(crops);
    }
    return Optional.empty();
  }

  /**
   * Método que atualiza uma fazenda.
   *
   * @param id    id da fazenda
   * @param crops DTO da plantação
   * @return Optional com a plantação atualizada ou um Optional vazio.
   */
  public Optional<Crops> updateCrops(Long id, Crops crops) {
    Optional<Crops> optionalCrops = cropsRepository.findById(id);
    if (optionalCrops.isPresent()) {
      Crops savedCrops = cropsRepository.save(crops);
      return Optional.of(savedCrops);
    }
    return Optional.empty();
  }

  public Optional<Farm> updateFarm(Long id, Farm farm) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);
    if (optionalFarm.isPresent()) {
      Farm savedFarm = farmRepository.save(farm);
      System.out.println(savedFarm);
      return Optional.of(savedFarm);
    }
    return Optional.empty();
  }
}
