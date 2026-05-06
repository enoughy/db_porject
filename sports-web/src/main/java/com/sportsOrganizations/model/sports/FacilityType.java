package com.sportsOrganizations.model.sports;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "facility_types")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class FacilityType extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String typeName;
}
