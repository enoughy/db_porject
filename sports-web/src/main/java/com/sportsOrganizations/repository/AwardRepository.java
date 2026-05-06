package com.sportsOrganizations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportsOrganizations.model.sports.Athlete;
import com.sportsOrganizations.model.sports.Award;

public interface AwardRepository extends JpaRepository<Award, Long> {

  @Query("""
          select a
          from Award a
          where a.competition.id = :competitionId
          order by a.place
      """)
  List<Award> findByCompetitionId(@Param("competitionId") Long competitionId);
}
