package com.sportsOrganizations.model.sports;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sports_facilities")
@Getter
@Setter
public class SportsFacility extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private String location;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "facility_type_id", nullable = false)
  private FacilityType facilityType;

  @OneToMany(mappedBy = "sportsFacility", cascade = CascadeType.ALL)
  private List<Competition> competitions = new ArrayList<>();
}
