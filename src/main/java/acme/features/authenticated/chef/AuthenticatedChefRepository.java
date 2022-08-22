package acme.features.authenticated.chef;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface AuthenticatedChefRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select c from Chef c where c.userAccount.id = :id")
	Chef findOneChefByUserAccountId(int id);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration systemConfiguration();
}
