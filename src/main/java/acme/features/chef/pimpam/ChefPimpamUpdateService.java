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
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefPimpamUpdateService implements AbstractUpdateService<Chef, Pimpam> {
	
	@Autowired
	protected ChefPimpamRepository repository;
	

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;

		boolean result;
		int id;
		Pimpam pimpam;
		Chef chef;

		id = request.getModel().getInteger("id");
		pimpam = this.repository.findPimpamById(id);
		chef = pimpam.getItem().getChef();
		result = !pimpam.getItem().getPublished() && request.isPrincipal(chef);
		return result;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "codigo", "titulo", "descripcion", "periodoInicial", "periodoFinal" ,"presupuesto", "enlace");
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("itemName", entity.getItem().getName());
		model.setAttribute("itemPublished", entity.getItem().getPublished());
		request.unbind(entity, model, "codigo", "titulo", "fechaCreacion", "descripcion", "periodoInicial", "periodoFinal" ,"presupuesto", "enlace");
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
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("codigo")) {
			Pimpam existing;
			int masterId;
			masterId = request.getModel().getInteger("id");
			existing = this.repository.findPimpamByCode(entity.getCodigo());
			if(this.repository.findPimpamById(masterId).equals(existing)) {
				
			}else {
				errors.state(request, existing == null, "codigo", "chef.item.form.error.duplicated");
			}
		}
		
		if(!errors.hasErrors("enlace")) {
			boolean isLink;
			if(!entity.getEnlace().isEmpty()) {
				isLink = (entity.getEnlace().startsWith("http") || entity.getEnlace().startsWith("www")) && entity.getEnlace().contains(".");
				errors.state(request, isLink, "link", "chef.item.form.error.link");
			}
		}
		
		if(!errors.hasErrors("periodoInicial")) {
			final Date minInitialDate=DateUtils.addMonths(entity.getFechaCreacion(), 1);

			errors.state(request,entity.getPeriodoInicial().after(minInitialDate), "periodoInicial", "epicure.dish.form.error.too-close-start-date");
			
		}
		if(!errors.hasErrors("periodoFinal") && !errors.hasErrors("periodoInicial")) {
			final Date minFinishDate=DateUtils.addWeeks(entity.getPeriodoInicial(), 1);
			
			errors.state(request,entity.getPeriodoFinal().after(minFinishDate), "periodoFinal", "chef.pimpam.form.error.one-week");
			
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
	public void update(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
