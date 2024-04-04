package com.betrybe.agrix.farm.repository;

import com.betrybe.agrix.farm.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Farm repository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
