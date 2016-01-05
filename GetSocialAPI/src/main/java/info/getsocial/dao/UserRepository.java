package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findById(Long id);

	User findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}
