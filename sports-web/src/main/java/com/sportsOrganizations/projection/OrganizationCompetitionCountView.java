package com.sportsOrganizations.projection;

public interface OrganizationCompetitionCountView {
  Long getOrganizationId();

  String getOrganizationName();

  Long getCompetitionsCount();
}
