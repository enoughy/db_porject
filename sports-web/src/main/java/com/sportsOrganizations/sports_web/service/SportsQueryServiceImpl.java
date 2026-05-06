package com.sportsOrganizations.sports_web.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsOrganizations.model.sports.Athlete;
import com.sportsOrganizations.model.sports.Award;
import com.sportsOrganizations.model.sports.Coach;
import com.sportsOrganizations.model.sports.Competition;
import com.sportsOrganizations.model.sports.SportsFacility;
import com.sportsOrganizations.projection.AthleteSportView;
import com.sportsOrganizations.projection.ClubParticipationView;
import com.sportsOrganizations.projection.FacilityCompetitionDateView;
import com.sportsOrganizations.projection.OrganizationCompetitionCountView;
import com.sportsOrganizations.repository.AthleteRepository;
import com.sportsOrganizations.repository.AwardRepository;
import com.sportsOrganizations.repository.CoachRepository;
import com.sportsOrganizations.repository.CompetitionRepository;
import com.sportsOrganizations.repository.SportsFacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SportsQueryServiceImpl implements SportsQueryService {

  private final SportsFacilityRepository sportsFacilityRepository;
  private final AthleteRepository athleteRepository;
  private final CoachRepository coachRepository;
  private final CompetitionRepository competitionRepository;
  private final AwardRepository awardRepository;

  @Override
  public List<SportsFacility> getFacilitiesByTypeAndCharacteristics(
      String typeName,
      Integer minCapacity,
      Double minAreaSize,
      Integer minSimulatorsCount) {
    return sportsFacilityRepository.findByTypeAndCharacteristics(
        typeName,
        minCapacity,
        minAreaSize,
        minSimulatorsCount);
  }

  @Override
  public List<Athlete> getAthletesBySportAndRank(Long sportId, Integer minRank) {
    return athleteRepository.findBySportAndMinRank(sportId, minRank);
  }

  @Override
  public List<Athlete> getAthletesByCoachAndRank(Long coachId, Integer minRank) {
    return athleteRepository.findByCoachAndMinRank(coachId, minRank);
  }

  @Override
  public List<AthleteSportView> getAthletesWithMoreThanOneSport() {
    return athleteRepository.findAthletesWithMoreThanOneSport();
  }

  @Override
  public List<Coach> getCoachesByAthlete(Long athleteId) {
    return athleteRepository.findCoachesByAthleteId(athleteId);
  }

  @Override
  public List<Competition> getCompetitionsByPeriodAndOrganization(
      LocalDateTime from,
      LocalDateTime to,
      Long organizationId) {
    return competitionRepository.findByPeriodAndOrganization(from, to, organizationId);
  }

  @Override
  public List<Award> getPrizeWinnersByCompetition(Long competitionId) {
    return awardRepository.findByCompetitionId(competitionId);
  }

  @Override
  public List<Competition> getCompetitionsByFacilityAndSport(Long facilityId, Long sportId) {
    return competitionRepository.findByFacilityAndSport(facilityId, sportId);
  }

  @Override
  public List<ClubParticipationView> getClubAthleteCountsInPeriod(
      LocalDateTime from,
      LocalDateTime to) {
    return athleteRepository.findClubAthleteCountsInPeriod(from, to);
  }

  @Override
  public List<Coach> getCoachesBySport(Long sportId) {
    return coachRepository.findBySportId(sportId);
  }

  @Override
  public List<Athlete> getAthletesWithoutCompetitionsInPeriod(
      LocalDateTime from,
      LocalDateTime to) {
    return athleteRepository.findAthletesWithoutCompetitionsInPeriod(from, to);
  }

  @Override
  public List<OrganizationCompetitionCountView> getOrganizationCompetitionCountsInPeriod(
      LocalDateTime from,
      LocalDateTime to) {
    return competitionRepository.countCompetitionsByOrganizationInPeriod(from, to);
  }

  @Override
  public List<FacilityCompetitionDateView> getFacilityCompetitionDatesInPeriod(
      LocalDateTime from,
      LocalDateTime to) {
    return competitionRepository.findFacilityCompetitionDatesInPeriod(from, to);
  }
}
