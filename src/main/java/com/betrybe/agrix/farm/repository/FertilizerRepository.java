package com.betrybe.agrix.farm.repository;

import com.betrybe.agrix.farm.entity.Fertilizers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FertilizerRepository.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizers, Long> {
}
