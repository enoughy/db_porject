package com.sportsOrganizations.sports_web.controlers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportsOrganizations.model.sports.Athlete;
import com.sportsOrganizations.model.sports.Award;
import com.sportsOrganizations.model.sports.Coach;
import com.sportsOrganizations.model.sports.Competition;
import com.sportsOrganizations.model.sports.SportsFacility;
import com.sportsOrganizations.projection.AthleteSportView;
import com.sportsOrganizations.projection.ClubParticipationView;
import com.sportsOrganizations.projection.FacilityCompetitionDateView;
import com.sportsOrganizations.projection.OrganizationCompetitionCountView;
import com.sportsOrganizations.sports_web.service.SportsQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/queries")
@RequiredArgsConstructor
public class SportsQueryController {

  private final SportsQueryService sportsQueryService;

  @GetMapping("/facilities")
  public ResponseEntity<List<SportsFacility>> getFacilitiesByTypeAndCharacteristics(
      @RequestParam String typeName,
      @RequestParam(required = false) Integer minCapacity,
      @RequestParam(required = false) Double minAreaSize,
      @RequestParam(required = false) Integer minSimulatorsCount) {
    return ResponseEntity.ok(
        sportsQueryService.getFacilitiesByTypeAndCharacteristics(
            typeName,
            minCapacity,
            minAreaSize,
            minSimulatorsCount));
  }

  @GetMapping("/athletes/by-sport")
  public ResponseEntity<List<Athlete>> getAthletesBySportAndRank(
      @RequestParam Long sportId,
      @RequestParam(required = false) Integer minRank) {
    return ResponseEntity.ok(
        sportsQueryService.getAthletesBySportAndRank(sportId, minRank));
  }

  @GetMapping("/athletes/by-coach")
  public ResponseEntity<List<Athlete>> getAthletesByCoachAndRank(
      @RequestParam Long coachId,
      @RequestParam(required = false) Integer minRank) {
    return ResponseEntity.ok(
        sportsQueryService.getAthletesByCoachAndRank(coachId, minRank));
  }

  @GetMapping("/athletes/multi-sport")
  public ResponseEntity<List<AthleteSportView>> getAthletesWithMoreThanOneSport() {
    return ResponseEntity.ok(
        sportsQueryService.getAthletesWithMoreThanOneSport());
  }

  @GetMapping("/athletes/{athleteId}/coaches")
  public ResponseEntity<List<Coach>> getCoachesByAthlete(
      @PathVariable Long athleteId) {
    return ResponseEntity.ok(
        sportsQueryService.getCoachesByAthlete(athleteId));
  }

  @GetMapping("/competitions")
  public ResponseEntity<List<Competition>> getCompetitionsByPeriodAndOrganization(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
      @RequestParam(required = false) Long organizationId) {
    return ResponseEntity.ok(
        sportsQueryService.getCompetitionsByPeriodAndOrganization(from, to, organizationId));
  }

  @GetMapping("/competitions/{competitionId}/prize-winners")
  public ResponseEntity<List<Award>> getPrizeWinnersByCompetition(
      @PathVariable Long competitionId) {
    return ResponseEntity.ok(
        sportsQueryService.getPrizeWinnersByCompetition(competitionId));
  }

  @GetMapping("/competitions/by-facility")
  public ResponseEntity<List<Competition>> getCompetitionsByFacilityAndSport(
      @RequestParam Long facilityId,
      @RequestParam(required = false) Long sportId) {
    return ResponseEntity.ok(
        sportsQueryService.getCompetitionsByFacilityAndSport(facilityId, sportId));
  }

  @GetMapping("/clubs/participation")
  public ResponseEntity<List<ClubParticipationView>> getClubAthleteCountsInPeriod(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
    return ResponseEntity.ok(
        sportsQueryService.getClubAthleteCountsInPeriod(from, to));
  }

  @GetMapping("/coaches/by-sport")
  public ResponseEntity<List<Coach>> getCoachesBySport(
      @RequestParam Long sportId) {
    return ResponseEntity.ok(
        sportsQueryService.getCoachesBySport(sportId));
  }

  @GetMapping("/athletes/not-in-competitions")
  public ResponseEntity<List<Athlete>> getAthletesWithoutCompetitionsInPeriod(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
    return ResponseEntity.ok(
        sportsQueryService.getAthletesWithoutCompetitionsInPeriod(from, to));
  }

  @GetMapping("/organizations/competition-count")
  public ResponseEntity<List<OrganizationCompetitionCountView>> getOrganizationCompetitionCountsInPeriod(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
    return ResponseEntity.ok(
        sportsQueryService.getOrganizationCompetitionCountsInPeriod(from, to));
  }

  @GetMapping("/facilities/competition-dates")
  public ResponseEntity<List<FacilityCompetitionDateView>> getFacilityCompetitionDatesInPeriod(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
    return ResponseEntity.ok(
        sportsQueryService.getFacilityCompetitionDatesInPeriod(from, to));
  }
}
