package com.sportsOrganizations.sports_web.dto;

public record ClubParticipationCountDto(
    Long clubId,
    String clubName,
    Long athletesCount) {
}
