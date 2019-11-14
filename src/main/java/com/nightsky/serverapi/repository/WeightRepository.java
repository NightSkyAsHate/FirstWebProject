package com.nightsky.serverapi.repository;

import com.nightsky.serverapi.domain.Weight;
import org.springframework.data.repository.CrudRepository;

public interface WeightRepository extends CrudRepository<Weight, Long> {
    Weight findByName(String name);
}