package pl.dgadecki.sonarqubeintroduction.business.user.domain;

import pl.dgadecki.sonarqubeintroduction.business.user.dto.User;

/**
 * Business facade for all User related operations.
 */
public interface UserFacade {

    /**
     * Returns currently authenticated user.
     *
     * @return {@link User} with the data of the currently authenticated user
     */
    User getAuthenticatedUser();
}
