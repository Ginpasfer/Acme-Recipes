package acme.features.epicure.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureDashboardRepository extends AbstractRepository{

	@Query("SELECT COUNT(p) FROM Dish p WHERE p.status = :status")
	Integer numDishByStatus(Status status);
	
	@Query("SELECT p.budget.currency, ROUND(AVG(p.budget.amount),2),p.status FROM Dish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> averageNumberOfBudgetsByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, ROUND(STDDEV(p.budget.amount),2),p.status FROM Dish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> deviationOfBudgetsByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, MIN(p.budget.amount),p.status FROM Dish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> minBudgetByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, MAX(p.budget.amount),p.status FROM Dish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> maxBudgetByCurrencyAndStatus(Status status);
}
