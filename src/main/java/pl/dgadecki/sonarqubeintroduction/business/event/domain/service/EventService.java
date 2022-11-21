package pl.dgadecki.sonarqubeintroduction.business.event.domain.service;

import pl.dgadecki.sonarqubeintroduction.business.event.dto.Event;
import pl.dgadecki.sonarqubeintroduction.business.event.dto.query.EventsQuery;

/**
 * Defines the business logic of events related processes.
 */
public interface EventService {

    /**
     * Finds {@link EventsQuery} that contains list of {@link Event} whose name includes the name given as a method parameter.
     *
     * @param name name that should contain the name of the event that will be found
     * @return {@link EventsQuery} hat contains list of {@link Event} with a name containing the name given as a parameter
     */
    EventsQuery findAllEventsByName(String name);
}
