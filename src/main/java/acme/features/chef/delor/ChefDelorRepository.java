package acme.features.chef.delor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.delor.Delor;
import acme.entities.item.Item;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface ChefDelorRepository extends AbstractRepository {
	
	@Query("select p from Delor p where p.id = :id")
	Delor findDelorById(int id);
	 
	@Query("select p from Delor p where p.item.chef.id = :chefId")
	Collection<Delor> findDelorsByChef(Integer chefId);
	
	@Query("select i from Item i where i.chef.id = :chefId and i.published = false and i.itemType=1 and i.id NOT IN (select p.item.id from Delor p)")
	Collection<Item> findAllItemsOfChef(Integer chefId);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id and i.itemType=1")
	Item findItemById(int id);
	
	@Query("SELECT p FROM Delor p WHERE p.keylet = :code")
	Delor findDelorByCode(String code);

	@Query("SELECT sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

	@Query("select c.defaultCurrency  from SystemConfiguration c")
	String getDefaultCurrency();	
	
}

