package acme.features.chef.delor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;



@Service
public class ChefDelorCreateService implements AbstractCreateService<Chef, Delor> {

	@Autowired
	protected ChefDelorRepository repository;
	

	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setInstantiationMoment(moment);
		entity.setItem(this.repository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));

		
		request.bind(entity, errors, "keylet", "subject", "explanation", "initialPeriod", "finalPeriod" ,"income", "moreInfo");
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("items", this.repository.findAllItemsOfChef(request.getPrincipal().getActiveRoleId()));
		
		request.unbind(entity, model, "keylet", "instantiationMoment", "subject", "explanation", "initialPeriod", "finalPeriod" ,"income", "moreInfo");
	}

	
	private String generateCode(final Integer id) {
		String result;
		
		final Date date = new Date();
		final ZoneId timeZone = ZoneId.systemDefault();
		final LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
		
		final Integer year = getLocalDate.getYear();
		final Integer month = getLocalDate.getMonthValue();
		final Integer day = getLocalDate.getDayOfMonth();
		
		final String anyo = year.toString().substring(2);
		String mes = month.toString();
		String dia = day.toString();
		
		String cod="";
		Integer seq=0;
		
		final List<Delor> delor = (List<Delor>) this.repository.findDelorsByChef(id);
		if (delor.isEmpty()) {
			seq = 1;
			cod="00000"+seq.toString();
		}else if(!delor.isEmpty() && delor.size() < 9){
			seq = delor.size()+1;
			cod="00000"+seq.toString();
		}else if (!delor.isEmpty() && delor.size() < 99){
			seq = delor.size()+1;
			cod="0000"+seq.toString();
		}else if (!delor.isEmpty() && delor.size() < 999){
			seq = delor.size()+1;
			cod="000"+seq.toString();
		}else if (!delor.isEmpty() && delor.size() < 9999){
			seq = delor.size()+1;
			cod="00"+seq.toString();
		}else if (!delor.isEmpty() && delor.size() < 99999){
			seq = delor.size()+1;
			cod="0"+seq.toString();
		} else {
			seq = delor.size()+1;
			cod=seq.toString();
		}
		
		if(mes.length()==1) {
			mes = "0" + mes;
		}
		
		if(day.toString().length()==1) {
			dia = "0" + dia.toString();
		}
		
		result =  cod + ":"+ anyo + mes + dia;
		return result;
	}
	
	@Override
	public Delor instantiate(final Request<Delor> request) {
		assert request != null;

		final Delor result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		Date initialD;
		Date finalD;

		initialD = DateUtils.addMonths( new Date(System.currentTimeMillis() + 300000),1);
		finalD = DateUtils.addWeeks( initialD,1);
		finalD = DateUtils.addMinutes(finalD, 1);
		final String keylet = this.generateCode(request.getPrincipal().getActiveRoleId());
	
		result = new Delor();
		
		result.setKeylet(keylet);
		result.setInstantiationMoment(moment);
		result.setSubject("");
		result.setExplanation("");
		result.setInitialPeriod(initialD);
		result.setFinalPeriod(finalD);
		result.setMoreInfo("");

		return result;
	}

	@Override
	public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("keylet")) {
			Delor existing;

			existing = this.repository.findDelorByCode(entity.getKeylet());
			errors.state(request, existing == null, "keylet", "chef.item.form.error.duplicated");
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
		if(!errors.hasErrors("finalPeriod") && !errors.hasErrors("initialPeriodDate")) {
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
	public void create(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}