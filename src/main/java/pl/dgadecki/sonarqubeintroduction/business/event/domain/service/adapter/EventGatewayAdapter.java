package pl.dgadecki.sonarqubeintroduction.business.event.domain.service.adapter;

import pl.dgadecki.sonarqubeintroduction.business.event.api.EventMessagePublisher;
import pl.dgadecki.sonarqubeintroduction.business.event.domain.service.EventGateway;
import pl.dgadecki.sonarqubeintroduction.business.event.dto.BookTicketNotification;
import pl.dgadecki.sonarqubeintroduction.business.external.discount.DiscountServiceClient;
import pl.dgadecki.sonarqubeintroduction.business.user.domain.UserFacade;
import pl.dgadecki.sonarqubeintroduction.business.user.dto.User;

public record EventGatewayAdapter(
        UserFacade userFacade,
        DiscountServiceClient discountServiceClient,
        EventMessagePublisher eventMessagePublisher
) implements EventGateway {

    @Override
    public User getAuthenticatedUser() {
        return userFacade.getAuthenticatedUser();
    }

    @Override
    public void publishBookTicketNotification(BookTicketNotification bookTicketNotification) {
        eventMessagePublisher.publishBookTicketNotification(bookTicketNotification);
    }

    @Override
    public Long getDiscountByDiscountCode(String discountCode) {
        return discountServiceClient.getDiscountByDiscountCode(discountCode);
    }
}
