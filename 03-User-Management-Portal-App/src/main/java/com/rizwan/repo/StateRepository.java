package com.rizwan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rizwan.entities.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Integer> {
public List<StateEntity>findByCountryId(Integer countryId);
}
