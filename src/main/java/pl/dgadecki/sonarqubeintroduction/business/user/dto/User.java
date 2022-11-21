package pl.dgadecki.sonarqubeintroduction.business.user.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record User(
        UUID uuid,
        String firstName,
        String lastName
) { }
