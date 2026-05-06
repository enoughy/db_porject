package com.sportsOrganizations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportsOrganizations.model.sports.Court;
import com.sportsOrganizations.model.sports.CourtCoatingType;

public interface CourtRepository extends JpaRepository<Court, Long> {
  List<Court> findByCoatingType(CourtCoatingType coatingType);
}
