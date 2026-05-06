package com.sportsOrganizations.model.sports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gyms")
@Getter
@Setter
public class Gym extends FacilityType {
  @Column(nullable = false) private Integer simulatorsCount;
  private long size;
}
