package acme.features.chef.delor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefDelorDeleteService implements AbstractDeleteService<Chef, Delor>{
	
	@Autowired
	protected ChefDelorRepository repository;


	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;

		boolean result;
		int idItem;
		final Delor delor;
		Chef chef;

		idItem = request.getModel().getInteger("id");
		delor = this.repository.findDelorById(idItem);
		chef = delor.getItem().getChef();
		result = !delor.getItem().getPublished() && request.isPrincipal(chef);

		return result;
	}

	@Override
	public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "keylet", "subject", "instantiationMoment", "explanation", "initialPeriod", "finalPeriod" ,"income", "moreInfo");
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
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
		

	}

	@Override
	public void delete(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}