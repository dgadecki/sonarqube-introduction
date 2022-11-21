package pl.dgadecki.sonarqubeintroduction.business.event.dto.query;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TicketPriceQuery(
        Long ticketId,
        BigDecimal standardPrice,
        BigDecimal discountPrice
) { }
