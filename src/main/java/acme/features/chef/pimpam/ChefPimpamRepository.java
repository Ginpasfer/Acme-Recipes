package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.pimpam.Pimpam;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface ChefPimpamRepository extends AbstractRepository {
	
	@Query("select p from Pimpam p where p.id = :id")
	Pimpam findPimpamById(int id);
	 
	@Query("select p from Pimpam p where p.item.chef.id = :chefId")
	Collection<Pimpam> findPimpamsByChef(Integer chefId);
	
	@Query("select i from Item i where i.chef.id = :chefId and i.published = false and i.id NOT IN (select p.item.id from Pimpam p)")
	Collection<Item> findAllItemsOfChef(Integer chefId);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	
	@Query("SELECT p FROM Pimpam p WHERE p.codigo = :code")
	Pimpam findPimpamByCode(String code);

	@Query("SELECT sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

	@Query("select c.defaultCurrency  from SystemConfiguration c")
	String getDefaultCurrency();	
	
}

