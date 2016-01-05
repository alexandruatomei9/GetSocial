package info.getsocial.security;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;

/**
 * A short-lived (per request) ConnectionRepository for a single user
 */
public class TemporaryConnectionRepository extends InMemoryUsersConnectionRepository {
    public TemporaryConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        super(connectionFactoryLocator);
    }
}
