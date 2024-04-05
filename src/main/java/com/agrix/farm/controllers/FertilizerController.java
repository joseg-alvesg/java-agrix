package com.agrix.farm.controllers;

import com.agrix.farm.controllers.dtos.FertilizerDto;
import com.agrix.farm.entity.Fertilizers;
import com.agrix.farm.services.FertilizerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
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
 * FertilizerController.
 */
@RestController
@RequestMapping("/fertilizers")
@Secured("ADMIN")
@SecurityRequirement(name = "Authentication")
@Tag(name = "Fertilizers", description = "Fertilizers need ADMIN permission")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Método que insere um novo fertilizante.
   *
   * @param fertilizerDto fertilizante a ser inserido
   * @return ResponseEntity com o fertilizante inserido.
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> insertFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizers fertilizer = fertilizerService
        .insertFertilizer(fertilizerDto.toEntity());
    FertilizerDto fertilizerDtoResponse = new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition());

    return new ResponseEntity<>(fertilizerDtoResponse, HttpStatus.CREATED);
  }

  /**
   * Método que retorna todos os fertilizantes.
   *
   * @return ResponseEntity com todos os fertilizantes ou uma mensagem de erro.
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizers> fertilizers = fertilizerService.getAllFertilizers();
    List<FertilizerDto> fertilizerDtos = fertilizers.stream().map(fertilizer -> new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition())).toList();
    return new ResponseEntity<>(fertilizerDtos, HttpStatus.OK);
  }

  /**
   * Método que retorna um fertilizante pelo seu id.
   *
   * @param id id do fertilizante
   * @return ResponseEntity com o fertilizante ou uma mensagem de erro.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFertilizerById(@PathVariable Long id) {
    Optional<Fertilizers> fertilizer = fertilizerService.getFertilizerById(id);
    if (fertilizer.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Fertilizante não encontrado!");
    }
    FertilizerDto fertilizerDto = new FertilizerDto(
        fertilizer.get().getId(),
        fertilizer.get().getName(),
        fertilizer.get().getBrand(),
        fertilizer.get().getComposition());
    return new ResponseEntity<>(fertilizerDto, HttpStatus.OK);
  }
}
