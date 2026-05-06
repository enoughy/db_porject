package com.sportsOrganizations.model.sports;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "competitions")
@Getter
@Setter
public class Competition extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "sports_facility_id", nullable = false)
  private SportsFacility sportsFacility;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "organization_id")
  private Organization organization;

  @ManyToMany
  @JoinTable(name = "competitions_participant", joinColumns = @JoinColumn(name = "competition_id"), inverseJoinColumns = @JoinColumn(name = "athlete_id"))

  private Set<Athlete> athetesParticipant = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "competitions_winers ", joinColumns = @JoinColumn(name = "competition_id"), inverseJoinColumns = @JoinColumn(name = "athlete_id"))
  private Set<Athlete> athletesWinners = new HashSet<>();

  @Column(nullable = false)
  private LocalDateTime competitionDate;

  @ManyToMany
  @JoinTable(name = "athlete_sports", joinColumns = @JoinColumn(name = "athlete_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
  private Set<Sport> sports = new HashSet<>();

  @OneToMany(mappedBy = "athlete")
  private Set<Training> trainings = new HashSet<>();

  @ManyToMany(mappedBy = "athletesParticipant")
  private Set<Competition> competitions = new HashSet<>();
}
