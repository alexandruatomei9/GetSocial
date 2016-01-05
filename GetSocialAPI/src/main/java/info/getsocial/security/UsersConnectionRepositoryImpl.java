package info.getsocial.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;

import info.getsocial.domain.User;

public class UsersConnectionRepositoryImpl implements UsersConnectionRepository {

	private SocialUserService userService;
	private ConnectionFactoryLocator connectionFactoryLocator;
	private ConnectionSignUp connectionSignUp;

	public UsersConnectionRepositoryImpl(SocialUserService userService,
			ConnectionFactoryLocator connectionFactoryLocator) {
		this.userService = userService;
		this.connectionFactoryLocator = connectionFactoryLocator;
	}

	@Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        try {
            User user = userService.loadUserByConnectionKey(connection.getKey());
            user.setAccessToken(connection.createData().getAccessToken());
            userService.updateUserDetails(user);
            return Arrays.asList(user.getUserId());
        } catch (AuthenticationException ae) {
            return Arrays.asList(connectionSignUp.execute(connection));
        }
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        Set<String> keys = new HashSet<>();
        for (String userId: providerUserIds) {
            ConnectionKey ck = new ConnectionKey(providerId, userId);
            try {
                keys.add(userService.loadUserByConnectionKey(ck).getUserId());
            } catch (AuthenticationException ae) {
                //ignore
            }
        }
        return keys;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        final ConnectionRepository connectionRepository = (ConnectionRepository) new TemporaryConnectionRepository(connectionFactoryLocator);
        final User user = userService.loadUserByUserId(userId);
        final ConnectionData connectionData = new ConnectionData(
                user.getProviderId(),
                user.getProviderUserId(),
                null, null, null,
                user.getAccessToken(),
                null, null, null);

        @SuppressWarnings("rawtypes")
		final Connection connection = connectionFactoryLocator
                .getConnectionFactory(user.getProviderId()).createConnection(connectionData);
        connectionRepository.addConnection(connection);
        return connectionRepository;
    }

    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }
}
