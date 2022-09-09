/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.chef.delor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefDelorListService implements AbstractListService<Chef, Delor> {
	
	@Autowired
	protected ChefDelorRepository repository;

	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Delor> findMany(final Request<Delor> request) {
		assert request != null;
		
		final Collection<Delor> result;
		
		result = this.repository.findDelorsByChef(request.getPrincipal().getActiveRoleId());

		return result;
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("itemName", entity.getItem().getName());
		
		request.unbind(entity, model, "subject");

	}

}
