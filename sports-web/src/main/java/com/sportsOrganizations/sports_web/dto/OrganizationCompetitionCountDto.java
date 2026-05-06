package com.sportsOrganizations.sports_web.dto;

public record OrganizationCompetitionCountDto(
    Long organizationId,
    String organizationName,
    Long competitionsCount) {
}
