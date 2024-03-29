package acme.features.administrator.dashboard;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("SELECT i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.KITCHEN_UTENSIL GROUP BY  i.retailPrice.currency")
	List<Object[]> findMetricsUtensilByCurrency();
	
	@Query("SELECT i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.INGREDIENT GROUP BY i.retailPrice.currency")
	List<Object[]> findMetricsIngredientsByCurrency();
	
	
	
	
	@Query("select count(i) from Item i where i.itemType = 0")
	Integer totalNumberOfUtensil();

	@Query("select count(i) from Item i where i.itemType = 1")
	Integer totalNumberOfIngredients();
	
	
	
	
	@Query("select count(p) from Dish p where p.status = 0")
	Integer ratioTotalNumberOfProposedDish();
	
	@Query("select avg(p.budget.amount) from Dish p where p.status = 0")
	Double averageBudgetOfProposedDish();

	@Query("select stddev(p.budget.amount) from Dish p where p.status = 0")
	Double deviationBudgetOfProposedDish();

	@Query("select min(p.budget.amount) from Dish p where p.status = 0")
	Double minimunBudgetOfProposedDish();

	@Query("select max(p.budget.amount) from Dish p where p.status = 0")
	Double maximumBudgetOfProposedDish();
	
	
	

	@Query("select count(p) from Dish p where p.status = 1")
	Integer ratioTotalNumberOfAcceptedDish();

	@Query("select avg(p.budget.amount) from Dish p where p.status = 1")
	Double averageBudgetOfAcceptedDish();

	@Query("select stddev(p.budget.amount) from Dish p where p.status = 1")
	Double deviationBudgetOfAcceptedDish();

	@Query("select min(p.budget.amount) from Dish p where p.status = 1")
	Double minimunBudgetOfAcceptedDish();

	@Query("select max(p.budget.amount) from Dish p where p.status = 1")
	Double maximumBudgetOfAcceptedDish();
	
	
	
	

	@Query("select count(p) from Dish p where p.status = 2")
	Integer ratioTotalNumberOfDeniedDish();

	@Query("select avg(p.budget.amount) from Dish p where p.status = 2")
	Double averageBudgetOfDeniedDish();

	@Query("select stddev(p.budget.amount) from Dish p where p.status = 2")
	Double deviationBudgetOfDeniedDish();

	@Query("select min(p.budget.amount) from Dish p where p.status = 2")
	Double minimunBudgetOfDeniedDish();

	@Query("select max(p.budget.amount) from Dish p where p.status = 2")
	Double maximumBudgetOfDeniedDish();
}
