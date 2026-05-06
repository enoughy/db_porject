package com.sportsOrganizations.sports_web.service;

import java.time.LocalDateTime;
import java.util.List;

import com.sportsOrganizations.model.sports.Athlete;
import com.sportsOrganizations.model.sports.Award;
import com.sportsOrganizations.model.sports.Coach;
import com.sportsOrganizations.model.sports.Competition;
import com.sportsOrganizations.model.sports.SportsFacility;
import com.sportsOrganizations.projection.AthleteSportView;
import com.sportsOrganizations.projection.ClubParticipationView;
import com.sportsOrganizations.projection.FacilityCompetitionDateView;
import com.sportsOrganizations.projection.OrganizationCompetitionCountView;

public interface SportsQueryService {

  List<SportsFacility> getFacilitiesByTypeAndCharacteristics(
      String typeName,
      Integer minCapacity,
      Double minAreaSize,
      Integer minSimulatorsCount);

  List<Athlete> getAthletesBySportAndRank(Long sportId, Integer minRank);

  List<Athlete> getAthletesByCoachAndRank(Long coachId, Integer minRank);

  List<AthleteSportView> getAthletesWithMoreThanOneSport();

  List<Coach> getCoachesByAthlete(Long athleteId);

  List<Competition> getCompetitionsByPeriodAndOrganization(
      LocalDateTime from,
      LocalDateTime to,
      Long organizationId);

  List<Award> getPrizeWinnersByCompetition(Long competitionId);

  List<Competition> getCompetitionsByFacilityAndSport(Long facilityId, Long sportId);

  List<ClubParticipationView> getClubAthleteCountsInPeriod(
      LocalDateTime from,
      LocalDateTime to);

  List<Coach> getCoachesBySport(Long sportId);

  List<Athlete> getAthletesWithoutCompetitionsInPeriod(
      LocalDateTime from,
      LocalDateTime to);

  List<OrganizationCompetitionCountView> getOrganizationCompetitionCountsInPeriod(
      LocalDateTime from,
      LocalDateTime to);

  List<FacilityCompetitionDateView> getFacilityCompetitionDatesInPeriod(
      LocalDateTime from,
      LocalDateTime to);
}
