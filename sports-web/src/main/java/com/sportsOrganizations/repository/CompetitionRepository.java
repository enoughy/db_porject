package com.sportsOrganizations.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportsOrganizations.model.sports.Competition;
import com.sportsOrganizations.projection.FacilityCompetitionDateView;
import com.sportsOrganizations.projection.OrganizationCompetitionCountView;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

  @Query("""
          select c
          from Competition c
          where c.competitionDate between :from and :to
            and (:organizationId is null or c.organization.id = :organizationId)
      """)
  List<Competition> findByPeriodAndOrganization(
      @Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to,
      @Param("organizationId") Long organizationId);

  @Query("""
          select c
          from Competition c
          where c.sportsFacility.id = :facilityId
            and (:sportId is null or c.sport.id = :sportId)
      """)
  List<Competition> findByFacilityAndSport(@Param("facilityId") Long facilityId,
      @Param("sportId") Long sportId);

  @Query("""
          select o.id as organizationId,
                 o.name as organizationName,
                 count(c) as competitionsCount
          from Competition c
          join c.organization o
          where c.competitionDate between :from and :to
          group by o.id, o.name
          order by o.name
      """)
  List<OrganizationCompetitionCountView> countCompetitionsByOrganizationInPeriod(
      @Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to);

  @Query("""
          select sf.id as facilityId,
                 sf.name as facilityName,
                 c.competitionDate as competitionDate
          from Competition c
          join c.sportsFacility sf
          where c.competitionDate between :from and :to
          order by sf.name, c.competitionDate
      """)
  List<FacilityCompetitionDateView> findFacilityCompetitionDatesInPeriod(
      @Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to);
}
