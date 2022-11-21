package pl.dgadecki.sonarqubeintroduction.business.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dgadecki.sonarqubeintroduction.business.user.domain.service.UserService;
import pl.dgadecki.sonarqubeintroduction.business.user.domain.service.adapter.UserServiceAdapter;

@Configuration
public class UserConfig {

    @Bean
    public UserFacade userFacade() {
        // Services
        UserService userService = new UserServiceAdapter();

        return new UserFacadeAdapter(
                userService
        );
    }
}
