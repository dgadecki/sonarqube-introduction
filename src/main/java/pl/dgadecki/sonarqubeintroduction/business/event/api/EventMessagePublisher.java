package pl.dgadecki.sonarqubeintroduction.business.event.api;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import pl.dgadecki.sonarqubeintroduction.business.event.dto.BookTicketNotification;
import pl.dgadecki.sonarqubeintroduction.common.RabbitMQConstants;

@Component
@RequiredArgsConstructor
public class EventMessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishBookTicketNotification(BookTicketNotification bookTicketNotification) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.NOTIFICATION_EXCHANGE_NAME, "", bookTicketNotification);
    }
}
