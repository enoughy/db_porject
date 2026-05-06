package com.sportsOrganizations.projection;

import java.time.LocalDateTime;

public interface FacilityCompetitionDateView {
  Long getFacilityId();

  String getFacilityName();

  LocalDateTime getCompetitionDate();
}
