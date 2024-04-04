package com.betrybe.agrix.farm.repository;

import com.betrybe.agrix.farm.entity.Crops;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Crops repository.
 */
@Repository
public interface CropsRepository extends JpaRepository<Crops, Long> {

  @Query("SELECT c FROM Crops c WHERE c.plantedDate BETWEEN ?1 AND ?2")
  List<Crops> findByPlantedDateBetween(LocalDate start, LocalDate end);

  @Query("SELECT c FROM Crops c WHERE c.harvestDate BETWEEN ?1 AND ?2")
  List<Crops> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
