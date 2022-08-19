package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.components.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard implements Serializable{

	// Serialisation identifier --------------------------------

	private static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	Map<Status, Integer> numberOfDishesByStatus;

	Map<Pair<Status, String>, Double>	averageNumberOfBudgetsByCurrencyAndStatus;

	Map<Pair<Status, String>, Double>	deviationOfBudgetsByCurrencyAndStatus;

	Map<Pair<Status, String>, Double>		maxBudgetByCurrencyAndStatus;

	Map<Pair<Status, String>, Double>		minBudgetByCurrencyAndStatus;
}
