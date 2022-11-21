package pl.dgadecki.sonarqubeintroduction.business.event.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dgadecki.sonarqubeintroduction.business.event.domain.model.EventTicketEntity;

public interface EventTicketRepository extends JpaRepository<EventTicketEntity, Long> { }
