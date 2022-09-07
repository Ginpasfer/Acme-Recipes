package acme.features.chef.pimpam;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;



@Service
public class ChefPimpamCreateService implements AbstractCreateService<Chef, Pimpam> {

	@Autowired
	protected ChefPimpamRepository repository;
	

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setFechaCreacion(moment);
		entity.setItem(this.repository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));

		
		request.bind(entity, errors, "codigo", "titulo", "descripcion", "periodoInicial", "periodoFinal" ,"presupuesto", "enlace");
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("items", this.repository.findAllItemsOfChef(request.getPrincipal().getActiveRoleId()));
		
		request.unbind(entity, model, "codigo", "fechaCreacion", "titulo", "descripcion", "periodoInicial", "periodoFinal" ,"presupuesto", "enlace");
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {
		assert request != null;

		final Pimpam result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		Date initialD;
		Date finalD;

		initialD = DateUtils.addMonths( new Date(System.currentTimeMillis() + 300000),1);
		finalD = DateUtils.addWeeks( initialD,1);
		finalD = DateUtils.addMinutes(finalD, 1);

	
		result = new Pimpam();
		result.setCodigo("");
		result.setFechaCreacion(moment);
		result.setTitulo("");
		result.setDescripcion("");
		result.setPeriodoInicial(initialD);
		result.setPeriodoFinal(finalD);
		result.setEnlace("");

		return result;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("codigo")) {
			Pimpam existing;

			existing = this.repository.findPimpamByCode(entity.getCodigo());
			errors.state(request, existing == null, "codigo", "chef.item.form.error.duplicated");
		}
		
		if(!errors.hasErrors("enlace")) {
			boolean isLink;
			if(!entity.getEnlace().isEmpty()) {
				isLink = (entity.getEnlace().startsWith("http") || entity.getEnlace().startsWith("www")) && entity.getEnlace().contains(".");
				errors.state(request, isLink, "link", "chef.item.form.error.link");
			}
		}
		
		if(!errors.hasErrors("presupuesto")) {
			final Double amount = entity.getPresupuesto().getAmount();
			
			final String[] acceptedCurrencies = this.repository.findAcceptedCurrencies().split(",");
			final Set<String> setCurrencies = new HashSet<String>();
			Collections.addAll(setCurrencies, acceptedCurrencies);
			final Boolean validCurrency = setCurrencies.contains(entity.getPresupuesto().getCurrency());
			
			errors.state(request, amount > 0., "presupuesto", "chef.item.form.error.retail-price-amount-negative-or-zero");
			errors.state(request, validCurrency, "presupuesto", "chef.item.form.error.retail-price-currency-invalid");
		}
		
		if(!errors.hasErrors("titulo")) {
			final boolean isNameSpam = SpamDetector.isSpam(entity.getTitulo(), this.repository.getSystemConfiguration());
			errors.state(request, !isNameSpam, "titulo", "Name contains spam");
		}
		
		if(!errors.hasErrors("descripcion")) {
			final boolean isDescriptionSpam = SpamDetector.isSpam(entity.getDescripcion(), this.repository.getSystemConfiguration());
			errors.state(request, !isDescriptionSpam, "descripcion", "Description contains spam");
		}
		
	}

	@Override
	public void create(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}