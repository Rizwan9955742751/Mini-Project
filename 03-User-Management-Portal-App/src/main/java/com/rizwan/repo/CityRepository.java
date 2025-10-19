package com.rizwan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rizwan.entities.CitiesEntity;

public interface CityRepository extends JpaRepository<CitiesEntity, Integer> {
public List<CitiesEntity>findByStateId(Integer stateId);
}
