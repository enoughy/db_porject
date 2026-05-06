package com.sportsOrganizations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportsOrganizations.model.sports.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {
  List<Gym> findBySimulatorsCountGreaterThanEqual(Integer count);
}
