package acme.features.chef.delor;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefDelorUpdateService implements AbstractUpdateService<Chef, Delor> {
	
	@Autowired
	protected ChefDelorRepository repository;
	

	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;

		boolean result;
		int id;
		Delor delor;
		Chef chef;

		id = request.getModel().getInteger("id");
		delor = this.repository.findDelorById(id);
		chef = delor.getItem().getChef();
		result = !delor.getItem().getPublished() && request.isPrincipal(chef);
		return result;
	}

	@Override
	public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "keylet", "subject", "explanation", "initialPeriod", "finalPeriod" ,"income", "moreInfo");
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("itemName", entity.getItem().getName());
		model.setAttribute("itemPublished", entity.getItem().getPublished());
		request.unbind(entity, model, "keylet", "subject", "instantiationMoment", "explanation", "initialPeriod", "finalPeriod" ,"income", "moreInfo");
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
	public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("keylet")) {
			Delor existing;
			int masterId;
			masterId = request.getModel().getInteger("id");
			existing = this.repository.findDelorByCode(entity.getKeylet());
			if(this.repository.findDelorById(masterId).equals(existing)) {
				
			}else {
				errors.state(request, existing == null, "keylet", "chef.item.form.error.duplicated");
			}
		}
		
		if(!errors.hasErrors("moreInfo")) {
			boolean isLink;
			if(!entity.getMoreInfo().isEmpty()) {
				isLink = (entity.getMoreInfo().startsWith("http") || entity.getMoreInfo().startsWith("www")) && entity.getMoreInfo().contains(".");
				errors.state(request, isLink, "moreInfo", "chef.item.form.error.link");
			}
		}
		
		if(!errors.hasErrors("initialPeriod")) {
			final Date minInitialDate=DateUtils.addMonths(entity.getInstantiationMoment(), 1);

			errors.state(request,entity.getInitialPeriod().after(minInitialDate), "initialPeriod", "epicure.dish.form.error.too-close-start-date");
			
		}
		if(!errors.hasErrors("finalPeriod") && !errors.hasErrors("initialPeriod")) {
			final Date minFinishDate=DateUtils.addWeeks(entity.getInitialPeriod(), 1);
			
			errors.state(request,entity.getFinalPeriod().after(minFinishDate), "finalPeriod", "chef.delor.form.error.one-week");
			
		}
		
		
		if(!errors.hasErrors("income")) {
			final Double amount = entity.getIncome().getAmount();
			
			final String[] acceptedCurrencies = this.repository.findAcceptedCurrencies().split(",");
			final Set<String> setCurrencies = new HashSet<String>();
			Collections.addAll(setCurrencies, acceptedCurrencies);
			final Boolean validCurrency = setCurrencies.contains(entity.getIncome().getCurrency());
			
			errors.state(request, amount > 0., "income", "chef.item.form.error.retail-price-amount-negative-or-zero");
			errors.state(request, validCurrency, "income", "chef.item.form.error.retail-price-currency-invalid");
		}
		
		if(!errors.hasErrors("subject")) {
			final boolean isSubjectSpam = SpamDetector.isSpam(entity.getSubject(), this.repository.getSystemConfiguration());
			errors.state(request, !isSubjectSpam, "subject", "Subject contains spam");
		}
		
		if(!errors.hasErrors("explanation")) {
			final boolean isExplanationSpam = SpamDetector.isSpam(entity.getExplanation(), this.repository.getSystemConfiguration());
			errors.state(request, !isExplanationSpam, "explanation", "Explanation contains spam");
		}
	}

	@Override
	public void update(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
