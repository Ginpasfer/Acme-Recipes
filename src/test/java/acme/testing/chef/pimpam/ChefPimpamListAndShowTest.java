package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamListAndShowTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpam-list-show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveChefPimpamListTest(final int recordIndex, final String key, final String titulo, final String codigo, final String fechaCreacion, final String descripcion, 
		final String periodoInicial, final String periodoFinal, final String presupuesto,final String enlace,final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, titulo);
		super.checkColumnHasValue(recordIndex, 1, item);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("titulo", titulo);
		super.checkInputBoxHasValue("codigo", codigo);
		super.checkInputBoxHasValue("fechaCreacion", fechaCreacion);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("periodoInicial", periodoInicial);
		super.checkInputBoxHasValue("periodoFinal", periodoFinal);
		super.checkInputBoxHasValue("presupuesto", presupuesto);
		super.checkInputBoxHasValue("itemName", item);
		super.checkInputBoxHasValue("enlace", enlace);
		
		super.signOut();
	}
	
	@Test
	@Order(10)
	public void hackingTest() {
		super.navigate("/chef/pimpam/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/item/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/item/list");
		super.checkPanicExists();
		super.signOut();
	}

}
