package com.sportsOrganizations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportsOrganizations.model.sports.SportsArea;

public interface SportsAreaRepository extends JpaRepository<SportsArea, Long> {
  List<SportsArea> findByAreaSizeGreaterThanEqual(Double areaSize);
}
