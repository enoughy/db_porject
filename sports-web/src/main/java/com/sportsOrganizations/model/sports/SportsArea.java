package com.sportsOrganizations.model.sports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sports_areas")
@Getter
@Setter
public class SportsArea extends FacilityType {

  @Column(nullable = false)
  private Double areaSize;
}
