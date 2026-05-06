package com.sportsOrganizations.model.sports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stadiums")
@Getter
@Setter
public class Stadium extends FacilityType {

  @Column(nullable = false) private Integer capacity;
}
