package acme.features.chef.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dish.Dish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefDishShowService implements AbstractShowService<Chef,Dish> {
	
	@Autowired
	protected ChefDishRepository repository;
	
	@Override
	public boolean authorise(final Request<Dish> request) {
		assert request != null;
		
		boolean result;
		int dishId;
		Dish dish;

		dishId = request.getModel().getInteger("id");
		dish = this.repository.findDishById(dishId);
		result = request.getPrincipal().getActiveRoleId() == dish.getChef().getId();

		return result;
	}

	@Override
	public Dish findOne(final Request<Dish> request) {
		assert request != null;

		Dish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findDishById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Dish> request, final Dish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "initialPeriodDate","finalPeriodDate","link");
		request.unbind(entity, model, "epicure.organisation", "epicure.assertion");
		request.unbind(entity, model, "epicure.userAccount.username");
		/*
		final String sysCurr = this.configRepository.getConfiguration().getSystemCurrency();
		
		if(!entity.getBudget().getCurrency().equals(sysCurr)) {
			final Money source = entity.getBudget();
			final MoneyExchange moneyEx = this.moneyExchangeService.computeMoneyExchange(source, sysCurr);
			model.setAttribute("convert", moneyEx.getTarget());
		}
		*/
	}
}