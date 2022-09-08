package acme.forms;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdministratorDashboard implements Serializable{

	// Serialisation identifier --------------------------------

	private static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	//Ingredients
	@Min(0)
	Integer	totalNumberOfIngredients;
	Map<String,Double> averageRetailPriceOfIngredientsGroupedByCurrency;
	Map<String,Double> deviationRetailPriceOfIngredientsGroupedByCurrency;
	Map<String,Double> minimunRetailPriceOfIngredientsGroupedByCurrency;
	Map<String,Double> maximunRetailPriceOfIngredientsGroupedByCurrency;
	
	//KitchenUtensils
	@Min(0)
	Integer totalNumberOfKitchenUtensils;
	Map<String,Double> averageRetailPriceOfKitchenUtensilsGroupedByCurrency;
	Map<String,Double> deviationRetailPriceOfKitchenUtensilsGroupedByCurrency;
	Map<String,Double> minimunRetailPriceOfKitchenUtensilsGroupedByCurrency;
	Map<String,Double> maximunRetailPriceOfKitchenUtensilsGroupedByCurrency;

	// FineDishes
	@Min(0)
	Integer	totalNumberOfProposedFineDishes;
	Integer	totalNumberOfAcceptedFineDishes;
	Integer	totalNumberOfDeniedFineDishes;
	
	Double averageBudgetOfFineDishesProposed;
	Double averageBudgetOfFineDishesAccepted;
	Double averageBudgetOfFineDishesDenied;
	
	Double deviationBudgetOfFineDishesProposed;
	Double deviationBudgetOfFineDishesAccepted;
	Double deviationBudgetOfFineDishesDenied;
	
	Double minimunBudgetOfFineDishesProposed;
	Double minimunBudgetOfFineDishesAccepted;
	Double minimunBudgetOfFineDishesDenied;
	
	Double maximunBudgetOfFineDishesProposed;
	Double maximunBudgetOfFineDishesAccepted;
	Double maximunBudgetOfFineDishesDenied;
	
	//Pimpams
	@Min(0)
	Integer totalNumberOfItemWithPimpam;
	Map<String,Double> averageBudgetOfPimpamGroupedByCurrency;
	Map<String,Double> deviationBudgetOfPimpamGroupedByCurrency;
	Map<String,Double> minimunBudgetOfPimpamGroupedByCurrency;
	Map<String,Double> maximunBudgetOfPimpamGroupedByCurrency;
	
}
