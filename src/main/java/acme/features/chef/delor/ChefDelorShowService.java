package acme.features.chef.delor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefDelorShowService implements AbstractShowService<Chef, Delor> {
	
	@Autowired
	protected ChefDelorRepository repository;
	
	@Autowired
	protected ChefDelorMoneyExchange chefDelorMoneyExchange;

	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		
		Delor delor;
		int id;
		int userId;
		boolean result;
		
		id = request.getModel().getInteger("id");
		delor = this.repository.findDelorById(id);
		userId = request.getPrincipal().getAccountId();

		result = userId == delor.getItem().getChef().getUserAccount().getId(); 
		return result;
		
	}

	@Override
	public Delor findOne(final Request<Delor> request) {
		assert request != null;
		Delor result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findDelorById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("itemName", entity.getItem().getName());
		model.setAttribute("items", this.repository.findAllItemsOfChef(request.getPrincipal().getActiveRoleId()));
		model.setAttribute("itemId", entity.getItem().getId());
		model.setAttribute("itemPublished", entity.getItem().getPublished());
		
		final String systemCurrency= this.repository.getDefaultCurrency();
		 MoneyExchange priceExchanged = null;
	     Integer i=0;
	        while (priceExchanged == null && i<=50) {
	        	priceExchanged=this.chefDelorMoneyExchange.computeMoneyExchange(entity.getIncome(), systemCurrency);
				i++;
			}
	        try {
				model.setAttribute("money", priceExchanged.getTarget());
			} catch (final Exception e) {
				model.setAttribute("money", "API unavailable at the moment");
			}
		request.unbind(entity, model, "keylet", "subject", "instantiationMoment", "explanation", "initialPeriod", "finalPeriod" ,"income", "moreInfo");
		
	}

}
