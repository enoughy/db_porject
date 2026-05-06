package com.sportsOrganizations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportsOrganizations.model.sports.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
  List<Stadium> findByCapacityGreaterThanEqual(Integer capacity);
}
