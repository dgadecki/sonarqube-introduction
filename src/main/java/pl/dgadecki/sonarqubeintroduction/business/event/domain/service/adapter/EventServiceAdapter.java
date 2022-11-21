package pl.dgadecki.sonarqubeintroduction.business.event.domain.service.adapter;

import pl.dgadecki.sonarqubeintroduction.business.event.domain.repository.EventRepository;
import pl.dgadecki.sonarqubeintroduction.business.event.domain.service.EventService;
import pl.dgadecki.sonarqubeintroduction.business.event.dto.Event;
import pl.dgadecki.sonarqubeintroduction.business.event.dto.query.EventsQuery;

import java.util.List;

public record EventServiceAdapter(
        EventRepository eventRepository
) implements EventService {

    @Override
    public EventsQuery findAllEventsByName(String name) {
        List<Event> events = eventRepository.findAllEventsByName(name);
        return EventsQuery.builder()
                .events(events)
                .build();
    }
}
