package acme.features.chef.pimpam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.pimpam.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;


@Controller
public class ChefPimpamController extends AbstractController<Chef,Pimpam> {
	
	@Autowired
	protected ChefPimpamListService listService;
		
	@Autowired
	protected ChefPimpamShowService showService;
	
	@Autowired
	protected ChefPimpamCreateService createService;
//	
//	@Autowired
//	protected ChefItemDeleteService deleteService;
//	
//	@Autowired
//	protected ChefItemUpdateService updateService;
//	
//	@Autowired
//	protected ChefItemPublishService publishService;
//	

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
//		super.addCommand("delete", this.deleteService);
//		super.addCommand("update", this.updateService);
//		super.addCommand("publish", "update", this.publishService);

	}

}
