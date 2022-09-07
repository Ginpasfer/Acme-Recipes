package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPimpamShowService implements AbstractShowService<Chef, Pimpam> {
	
	@Autowired
	protected ChefPimpamRepository repository;
	
//	@Autowired
//	protected ChefItemMoneyExchange chefItemMoneyExchange;

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		
		Pimpam pimpam;
		int id;
		int userId;
		boolean result;
		
		id = request.getModel().getInteger("id");
		pimpam = this.repository.findPimpamById(id);
		userId = request.getPrincipal().getAccountId();

		result = userId == pimpam.getItem().getChef().getUserAccount().getId(); 
		return result;
		
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		Pimpam result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findPimpamById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("itemName", entity.getItem().getName());
		model.setAttribute("items", this.repository.findAllItemsOfChef(request.getPrincipal().getActiveRoleId()));
		model.setAttribute("itemId", entity.getItem().getId());
		model.setAttribute("itemPublished", entity.getItem().getPublished());
		
//		final String systemCurrency= this.repository.getDefaultCurrency();
//		 MoneyExchange priceExchanged = null;
//	     Integer i=0;
//	        while (priceExchanged == null && i<=50) {
//	        	priceExchanged=this.chefItemMoneyExchange.computeMoneyExchange(entity.getRetailPrice(), systemCurrency);
//				i++;
//			}
//	        try {
//				model.setAttribute("money", priceExchanged.getTarget());
//			} catch (final Exception e) {
//				model.setAttribute("money", "API unavailable at the moment");
//			}
		request.unbind(entity, model, "codigo", "titulo", "fechaCreacion", "descripcion", "periodoInicial", "periodoFinal" ,"presupuesto", "enlace");
		
	}

}
