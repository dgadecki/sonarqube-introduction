package pl.dgadecki.sonarqubeintroduction.business.event.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Event(
        Long id,
        String name,
        String description,
        LocalDateTime startTime
) { }
