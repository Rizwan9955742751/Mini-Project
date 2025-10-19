package com.rizwan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rizwan.entities.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

}
