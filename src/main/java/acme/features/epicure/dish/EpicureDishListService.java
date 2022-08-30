package acme.features.epicure.dish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dish.Dish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureDishListService implements AbstractListService<Epicure,Dish>{
	
	@Autowired
	protected EpicureDishRepository repository;
	
	@Autowired
	protected EpicureDishMoneyExchange epicureDishMoneyExchange;
	
	@Override
	public boolean authorise(final Request<Dish> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<Dish> findMany(final Request<Dish> request) {
		assert request != null;
		
		Principal principal;
		int epicureId;
		Collection<Dish> result;
		
		principal = request.getPrincipal();
		epicureId = principal.getActiveRoleId();
		result = this.repository.findDishByEpicureId(epicureId);

		return result;
		
	}
	
	@Override
	public void unbind(final Request<Dish> request, final Dish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		 final String systemCurrency= this.repository.getDefaultCurrency();
			final Money priceExchanged=this.epicureDishMoneyExchange.computeMoneyExchange(entity.getBudget(), systemCurrency).getTarget();
			model.setAttribute("money", priceExchanged);
		
		request.unbind(entity, model, "status", "code", "budget", "creationDate");
	}

}