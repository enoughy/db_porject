package com.sportsOrganizations.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportsOrganizations.model.sports.Athlete;
import com.sportsOrganizations.model.sports.Coach;
import com.sportsOrganizations.projection.AthleteSportView;
import com.sportsOrganizations.projection.ClubParticipationView;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

  @Query("""
          select distinct a
          from Athlete a
          join a.sports s
          where s.id = :sportId
            and (:minRank is null or a.sportsRank >= :minRank)
      """)
  List<Athlete> findBySportAndMinRank(@Param("sportId") Long sportId,
      @Param("minRank") Integer minRank);

  @Query("""
          select distinct a
          from Athlete a
          join a.trainings t
          where t.coach.id = :coachId
            and (:minRank is null or a.sportsRank >= :minRank)
      """)
  List<Athlete> findByCoachAndMinRank(@Param("coachId") Long coachId,
      @Param("minRank") Integer minRank);

  @Query("""
          select a.fullName as athleteName, s.name as sportName
          from Athlete a
          join a.sports s
          where a.id in (
              select a2.id
              from Athlete a2
              join a2.sports s2
              group by a2.id
              having count(distinct s2.id) > 1
          )
          order by a.fullName, s.name
      """)
  List<AthleteSportView> findAthletesWithMoreThanOneSport();

  @Query("""
          select distinct c
          from Coach c
          join c.trainings t
          where t.athlete.id = :athleteId
      """)
  List<Coach> findCoachesByAthleteId(@Param("athleteId") Long athleteId);

  @Query("""
          select cl.id as clubId,
                 cl.name as clubName,
                 count(distinct a.id) as athletesCount
          from Competition c
          join c.athletesParticipant a
          join a.club cl
          where c.competitionDate between :from and :to
          group by cl.id, cl.name
          order by cl.name
      """)
  List<ClubParticipationView> findClubAthleteCountsInPeriod(@Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to);

  @Query("""
          select a
          from Athlete a
          where not exists (
              select 1
              from Competition c
              join c.athletesParticipant p
              where p = a
                and c.competitionDate between :from and :to
          )
      """)
  List<Athlete> findAthletesWithoutCompetitionsInPeriod(@Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to);
}
