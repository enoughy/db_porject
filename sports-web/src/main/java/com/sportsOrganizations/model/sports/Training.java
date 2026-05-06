package com.sportsOrganizations.model.sports;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trainings")
@Getter
@Setter
public class Training {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "sport_id")
  Sport sport;

  @ManyToOne
  @JoinColumn(name = "coach_id")
  Coach coach;

  @ManyToOne
  @JoinColumn(name = "athlete_id")
  Athlete athlete;

}
