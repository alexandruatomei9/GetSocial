package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import info.getsocial.domain.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

	UserAccount findByUsername(String username);

	UserAccount findById(Long id);

	UserAccount findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}
