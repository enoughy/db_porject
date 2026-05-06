package com.sportsOrganizations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportsOrganizations.model.sports.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {

  @Query("""
          select distinct c
          from Coach c
          join c.trainings t
          where t.sport.id = :sportId
      """)
  List<Coach> findBySportId(@Param("sportId") Long sportId);
}
