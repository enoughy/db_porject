package com.sportsOrganizations.model.sports;

import jakarta.persistence.Column;
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
@Table(name = "awards")
@Getter
@Setter
public class Award {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "athlet_id")
  private Athlete athlete;

  @ManyToOne
  @JoinColumn(name = "competition_id")
  private Competition competition;

  @Column(nullable = false)
  private Integer place; // 1, 2, 3
}
