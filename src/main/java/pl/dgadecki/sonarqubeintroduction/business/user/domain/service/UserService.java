package pl.dgadecki.sonarqubeintroduction.business.user.domain.service;

import pl.dgadecki.sonarqubeintroduction.business.user.dto.User;

/**
 * Defines the business logic of user related processes.
 */
public interface UserService {

    /**
     * Returns currently authenticated user.
     *
     * @return {@link User} with the data of the currently authenticated user
     */
    User getAuthenticatedUser();
}
