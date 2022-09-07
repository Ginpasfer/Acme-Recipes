package acme.features.chef.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefItemDeleteService implements AbstractDeleteService<Chef, Item>{
	
	@Autowired
	protected ChefItemRepository repository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int idItem;
		Item item;
		Chef chef;

		idItem = request.getModel().getInteger("id");
		item = this.repository.findItemById(idItem);
		chef = item.getChef();
		result = !item.getPublished() && request.isPrincipal(chef);

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "name", "code", "description", "published", "retailPrice", "link");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "code", "description", "published", "retailPrice", "itemTypes", "link");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		final Pimpam pimpam = this.repository.findPimpamByItemId(entity.getId());
        if(pimpam != null) {
        	this.repository.delete(pimpam);
        }
		this.repository.delete(entity);
	}

}