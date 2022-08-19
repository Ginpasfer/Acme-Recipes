package acme.features.epicure.dashboard;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.components.Status;
import acme.forms.EpicureDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard>{

	@Autowired
	protected EpicureDashboardRepository repository;


	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request != null;
		boolean res;
		res = request.getPrincipal().hasRole(Epicure.class);
		return res;
	}

	@Override
	public EpicureDashboard findOne(final Request<EpicureDashboard> request) {
		assert request != null;

		final EpicureDashboard res;
		
		final Map<Status, Integer> numberOfDishesByStatus = new EnumMap<>(Status.class);
		
		final Integer numberOfProposedDishes = this.repository.numDishByStatus(Status.PROPOSED);
		final Integer numberOfAcceptedDishes = this.repository.numDishByStatus(Status.ACCEPTED);
		final Integer numberOfDeniedDishes = this.repository.numDishByStatus(Status.DENIED);
		
		numberOfDishesByStatus.put(Status.PROPOSED, numberOfProposedDishes);
		numberOfDishesByStatus.put(Status.ACCEPTED, numberOfAcceptedDishes);
		numberOfDishesByStatus.put(Status.DENIED, numberOfDeniedDishes);
		
		final Map<Pair<Status, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> avgBudgetByCurrencyAccep = this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> avgBudgetByCurrencyDenied = this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.DENIED);
		final List<String> avgBudgetByCurrencyProposed = this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.PROPOSED);

		final List<String> avgBudgetByCurrency = new ArrayList<String>();
		avgBudgetByCurrency.addAll(avgBudgetByCurrencyAccep);
		avgBudgetByCurrency.addAll(avgBudgetByCurrencyDenied);
		avgBudgetByCurrency.addAll(avgBudgetByCurrencyProposed);
		
		for(final String list: avgBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			averageNumberOfBudgetsByCurrencyAndStatus.put(key, money);
		}
		
		final Map<Pair<Status, String>, Double> deviationOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> devBudgetByCurrencyAccep = this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> devBudgetByCurrencyDenied = this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.DENIED);
		final List<String> devBudgetByCurrencyProposed = this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.PROPOSED);

		final List<String> devBudgetByCurrency = new ArrayList<String>();
		devBudgetByCurrency.addAll(devBudgetByCurrencyAccep);
		devBudgetByCurrency.addAll(devBudgetByCurrencyDenied);
		devBudgetByCurrency.addAll(devBudgetByCurrencyProposed);
		
		for(final String list: devBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			deviationOfBudgetsByCurrencyAndStatus.put(key, money);
		}
		

		final Map<Pair<Status, String>, Double> maxBudgetByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> maxBudgetByCurrencyAccep = this.repository.maxBudgetByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> maxBudgetByCurrencyDenied = this.repository.maxBudgetByCurrencyAndStatus(Status.DENIED);
		final List<String> maxBudgetByCurrencyProposed = this.repository.maxBudgetByCurrencyAndStatus(Status.PROPOSED);

		final List<String> maxBudgetByCurrency = new ArrayList<String>();
		maxBudgetByCurrency.addAll(maxBudgetByCurrencyAccep);
		maxBudgetByCurrency.addAll(maxBudgetByCurrencyDenied);
		maxBudgetByCurrency.addAll(maxBudgetByCurrencyProposed);
		
		for(final String list: maxBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			maxBudgetByCurrencyAndStatus.put(key, money);
		}
		
		final Map<Pair<Status, String>, Double> minBudgetByCurrencyAndStatus = new HashMap<Pair<Status,String>, Double>();
		final List<String> minBudgetByCurrencyAccep = this.repository.minBudgetByCurrencyAndStatus(Status.ACCEPTED);
		final List<String> minBudgetByCurrencyDenied = this.repository.minBudgetByCurrencyAndStatus(Status.DENIED);
		final List<String> minBudgetByCurrencyProposed = this.repository.minBudgetByCurrencyAndStatus(Status.PROPOSED);

		final List<String> minBudgetByCurrency = new ArrayList<String>();
		minBudgetByCurrency.addAll(minBudgetByCurrencyAccep);
		minBudgetByCurrency.addAll(minBudgetByCurrencyDenied);
		minBudgetByCurrency.addAll(minBudgetByCurrencyProposed);
		
		for(final String list: minBudgetByCurrency) {
			final String[] field = list.split(",");
			final Double money = Double.parseDouble(field[1].trim());
			final String currency = field[0].trim();
			final Status status = Status.valueOf(field[2].trim());
			final Pair<Status,String> key = Pair.of(status, currency);
			minBudgetByCurrencyAndStatus.put(key, money);
		}
		
		
		
		res = new EpicureDashboard();
		res.setNumberOfDishesByStatus(numberOfDishesByStatus);
		res.setAverageNumberOfBudgetsByCurrencyAndStatus(averageNumberOfBudgetsByCurrencyAndStatus);
		res.setDeviationOfBudgetsByCurrencyAndStatus(deviationOfBudgetsByCurrencyAndStatus);
		res.setMinBudgetByCurrencyAndStatus(minBudgetByCurrencyAndStatus);
		res.setMaxBudgetByCurrencyAndStatus(maxBudgetByCurrencyAndStatus);
		
		return res;
		
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"numberOfDishesByStatus", "averageNumberOfBudgetsByCurrencyAndStatus",
			"deviationOfBudgetsByCurrencyAndStatus", "minBudgetByCurrencyAndStatus","maxBudgetByCurrencyAndStatus");
	
	}
}
