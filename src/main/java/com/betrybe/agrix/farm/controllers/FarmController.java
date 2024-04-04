package com.betrybe.agrix.farm.controllers;

import com.betrybe.agrix.farm.controllers.dtos.CropsDto;
import com.betrybe.agrix.farm.controllers.dtos.FarmDto;
import com.betrybe.agrix.farm.entity.Crops;
import com.betrybe.agrix.farm.entity.Farm;
import com.betrybe.agrix.farm.services.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm controller.
 */
@RestController
@RequestMapping("/farms")
@Secured({ "USER", "MANAGER", "ADMIN" })
public class FarmController {

  /**
   * Farm service.
   */
  private final FarmService farmService;

  /**
   * Constructor that receives a farm service.
   *
   * @param farmService Farm service.
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Insert a new farm.
   *
   * @param farmDto that contains the farm data(id,name,size).
   * @return FarmDto.
   */
  @PostMapping
  public ResponseEntity<FarmDto> insertFarm(@RequestBody FarmDto farmDto) {
    Farm farm = farmService.insertFarm(farmDto.toEntity());
    FarmDto farmDt = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return new ResponseEntity<>(farmDt, HttpStatus.CREATED);
  }

  /**
   * Método que retorna todas as fazendas.
   *
   * @return ResponseEntity com todas as fazendas ou uma mensagem de erro.
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarm() {
    List<Farm> farms = farmService.getAllFarm();
    List<FarmDto> farmDtos = farms.stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
    return new ResponseEntity<>(farmDtos, HttpStatus.OK);
  }

  /**
   * Método que retorna uma fazenda pelo seu id.
   *
   * @param id id da fazenda.
   * @return ResponseEntity com a fazenda encontrada ou uma mensagem de erro.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarmById(@PathVariable Long id) {
    Optional<Farm> optionalFarm = farmService.getFarmById(id);
    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Fazenda não encontrada!");
    }
    Farm farm = optionalFarm.get();
    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return ResponseEntity.ok(farmDto);
  }

  /**
   * Método que insere uma plantação em uma fazenda.
   *
   * @param farmId   id da fazenda.
   * @param cropsDto DTO da plantação.
   * @return ResponseEntity com a plantação inserida ou uma mensagem de erro.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<?> insertCrops(@PathVariable Long farmId,
      @RequestBody CropsDto cropsDto) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);
    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Fazenda não encontrada!");
    }
    Crops crops = cropsDto.toEntity();
    Optional<Crops> optionalCrops = farmService.insertCrops(farmId, crops);
    CropsDto cropsDt = new CropsDto(
        optionalCrops.get().getId(),
        optionalCrops.get().getName(),
        optionalCrops.get().getPlantedArea(),
        optionalCrops.get().getFarm().getId(),
        optionalCrops.get().getPlantedDate(),
        optionalCrops.get().getHarvestDate());
    return ResponseEntity.status(HttpStatus.CREATED).body(cropsDt);
  }

  /**
   * Método que retorna todas as plantações de uma fazenda.
   *
   * @param farmId id da fazenda.
   * @return ResponseEntity com todas as plantações da fazenda ou uma mensagem de
   *         erro.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getAllCropsByFarmId(@PathVariable Long farmId) {
    Optional<List<Crops>> optionalCrops = farmService.getAllCropsByFarmId(farmId);
    if (optionalCrops.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Fazenda não encontrada!");
    }
    List<Crops> crops = optionalCrops.get();
    List<CropsDto> cropsDtos = crops.stream()
        .map(crop -> new CropsDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(cropsDtos);
  }
}
