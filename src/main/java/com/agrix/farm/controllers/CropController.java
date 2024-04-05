package com.agrix.farm.controllers;

import com.agrix.farm.controllers.dtos.CropsDto;
import com.agrix.farm.controllers.dtos.FertilizerDto;
import com.agrix.farm.entity.Crops;
import com.agrix.farm.entity.Fertilizers;
import com.agrix.farm.services.CropService;
import com.agrix.farm.services.FertilizerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop controller.
 */
@RestController
@RequestMapping("/crops")
@Secured({ "MANAGER", "ADMIN" })
@SecurityRequirement(name = "Authentication")
@Tag(name = "Crops", description = "Crops need MANAGER or ADMIN permission")
public class CropController {

  /**
   * Crop service.
   */
  private final CropService cropService;
  private final FertilizerService fertilizerService;

  /**
   * Construtor da classe.
   *
   * @param cropService       serviço de plantações.
   * @param fertilizerService serviço de fertilizantes.
   */
  @Autowired
  public CropController(
      CropService cropService,
      FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Método que retorna todas as plantações.
   *
   * @return ResponseEntity com todas as plantações ou uma mensagem de erro.
   */
  @GetMapping
  public ResponseEntity<List<CropsDto>> getAllCrops() {
    List<Crops> crops = cropService.getAllCrops();
    List<CropsDto> cropsDtos = crops.stream().map(crop -> new CropsDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate())).toList();
    return new ResponseEntity<>(cropsDtos, HttpStatus.OK);
  }

  /**
   * Método que retorna uma plantação pelo seu id.
   *
   * @param id id da plantação
   * @return ResponseEntity com a plantação encontrada ou uma mensagem de erro.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getCropsById(@PathVariable Long id) {
    Optional<Crops> optionalCrops = cropService.getCropById(id);
    if (optionalCrops.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Plantação não encontrada!");

    }
    Crops crops = optionalCrops.get();
    CropsDto cropsDto = new CropsDto(
        crops.getId(),
        crops.getName(),
        crops.getPlantedArea(),
        crops.getFarm().getId(),
        crops.getPlantedDate(),
        crops.getHarvestDate());
    return ResponseEntity.ok(Optional.of(cropsDto));
  }

  /**
   * Método que retorna todas as plantações de uma fazenda.
   *
   * @param start data de início da colheita
   * @param end   data de fim da colheita
   *
   * @return ResponseEntity com todas as plantações ou uma mensagem de erro.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropsDto>> searchCrops(
      @RequestParam("start") LocalDate start,
      @RequestParam("end") LocalDate end) {
    List<Crops> crops = cropService.getCropsByHarvestDate(start, end);
    List<CropsDto> cropsDtos = crops.stream().map(crop -> new CropsDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate())).toList();
    return ResponseEntity.ok(cropsDtos);

  }

  /**
   * Método que insere uma plantação.
   *
   * @param cropId       id da plantação
   * @param fertilizerId id do fertilizante
   * @return ResponseEntity com a mensagem de sucesso ou erro.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<?> insertFertilizerInCrop(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    Optional<Crops> optionalCrops = cropService.getCropById(cropId);
    if (optionalCrops.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Plantação não encontrada!");
    }
    Optional<Fertilizers> optionalFertilizers = fertilizerService
        .getFertilizerById(fertilizerId);
    if (optionalFertilizers.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Fertilizante não encontrado!");
    }

    Crops crops = optionalCrops.get();
    Fertilizers fertilizers = optionalFertilizers.get();

    crops.getFertilizers().add(fertilizers);
    fertilizers.getCrops().add(crops);

    cropService.insertCrop(crops);
    fertilizerService.insertFertilizer(fertilizers);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");

  }

  /**
   * Método que retorna todos os fertilizantes de uma plantação.
   *
   * @param cropId id da plantação
   * @return ResponseEntity com os fertilizantes encontrados ou uma mensagem de
   *         erro.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<?> getCropsFertilizers(@PathVariable Long cropId) {
    Optional<Crops> optionalCrops = cropService.getCropById(cropId);
    if (optionalCrops.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Plantação não encontrada!");
    }
    Crops crops = optionalCrops.get();
    List<FertilizerDto> fertilizers = crops.getFertilizers().stream()
        .map(fertilizer -> new FertilizerDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()))
        .toList();
    return ResponseEntity.ok(fertilizers);
  }
}
