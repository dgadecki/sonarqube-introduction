package pl.dgadecki.sonarqubeintroduction.business.external.discount;

public interface DiscountServiceClient {

    /**
     * Returns the discount value as a percentage for the provided discount code.
     *
     * @param discountCode the discount code for which the discount is to be found
     * @return value of the discount in percentage
     */
    Long getDiscountByDiscountCode(String discountCode);
}
