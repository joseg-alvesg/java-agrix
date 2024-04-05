package com.agrix.farm.repository;

import com.agrix.farm.entity.Fertilizers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FertilizerRepository.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizers, Long> {
}
