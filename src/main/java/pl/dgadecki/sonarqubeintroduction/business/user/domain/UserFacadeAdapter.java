package pl.dgadecki.sonarqubeintroduction.business.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.dgadecki.sonarqubeintroduction.business.user.domain.service.UserService;
import pl.dgadecki.sonarqubeintroduction.business.user.dto.User;

@Transactional
@RequiredArgsConstructor
public class UserFacadeAdapter implements UserFacade {

    private final UserService userService;

    @Override
    public User getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }
}
