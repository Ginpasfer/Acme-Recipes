package acme.features.authenticated.epicure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;

@Repository
public interface AuthenticatedEpicureRepository extends AbstractRepository {

	@Query("select p from Epicure p where p.userAccount.id = :id")
	Epicure findOneEpicureByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration systemConfiguration();

}
