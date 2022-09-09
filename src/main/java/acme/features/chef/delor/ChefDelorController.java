package acme.features.chef.delor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.delor.Delor;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;


@Controller
public class ChefDelorController extends AbstractController<Chef,Delor> {
	
	@Autowired
	protected ChefDelorListService listService;
		
	@Autowired
	protected ChefDelorShowService showService;
	
	@Autowired
	protected ChefDelorCreateService createService;
	
	@Autowired
	protected ChefDelorDeleteService deleteService;
	
	@Autowired
	protected ChefDelorUpdateService updateService;
	

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);


	}

}
