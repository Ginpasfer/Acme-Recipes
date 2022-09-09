package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/delete-pimpam.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String titulo) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, titulo);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		super.signOut();
	}
	

}