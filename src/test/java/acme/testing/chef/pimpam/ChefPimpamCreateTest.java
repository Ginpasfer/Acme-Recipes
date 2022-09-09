package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/create-pimpam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(4)
	public void positiveChefPimpamCreateTest(final int recordIndex, final String key, final String titulo,
		final String periodoInicial, final String periodoFinal, final String descripcion, final String presupuesto, final String enlace, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "Create a pimpam");
	   
		super.checkFormExists();
		
		super.fillInputBoxIn("titulo", titulo);
		super.fillInputBoxIn("periodoInicial", periodoInicial);
		super.fillInputBoxIn("periodoFinal", periodoFinal);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("presupuesto", presupuesto);
		super.fillInputBoxIn("enlace", enlace);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, titulo);
		super.checkColumnHasValue(recordIndex, 1, item);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("titulo", titulo);
		super.checkInputBoxHasValue("periodoInicial", periodoInicial);
		super.checkInputBoxHasValue("periodoFinal", periodoFinal);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("presupuesto", presupuesto);
		super.checkInputBoxHasValue("enlace", enlace);
		super.checkInputBoxHasValue("itemName", item);
			
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/create-pimpam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(3)
	public void negativeChefPimpamCreateTest(final int recordIndex, final String key, final String titulo,
		final String periodoInicial, final String periodoFinal, final String descripcion, final String presupuesto, final String enlace, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "Create a pimpam");
		
		
		super.fillInputBoxIn("titulo", titulo);
		super.fillInputBoxIn("periodoInicial", periodoInicial);
		super.fillInputBoxIn("periodoFinal", periodoFinal);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("presupuesto", presupuesto);
		super.fillInputBoxIn("enlace", enlace);
		
		super.clickOnSubmit("Create");

		super.checkErrorsExist();
		super.signOut();
	}

	@Test
	@Order(10)
	public void hackingTest() {
		super.navigate("/chef/pimpam/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/item/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/item/create");
		super.checkPanicExists();
		super.signOut();
	
	}
	
}
