package pl.dgadecki.sonarqubeintroduction.business.event.dto.query;

import lombok.Builder;
import pl.dgadecki.sonarqubeintroduction.business.event.dto.Event;

import java.util.List;

@Builder
public record EventsQuery(
        List<Event> events
) { }
