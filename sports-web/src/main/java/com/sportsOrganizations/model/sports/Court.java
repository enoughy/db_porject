package com.sportsOrganizations.model.sports;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courts")
@Getter
@Setter
public class Court extends FacilityType {

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CourtCoatingType coatingType;
}
