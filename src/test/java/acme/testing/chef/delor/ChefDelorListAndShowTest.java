package acme.testing.chef.delor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefDelorListAndShowTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/delor/delor-list-show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveChefDelorListTest(final int recordIndex, final String key, final String subject, final String keylet, final String instantiationMoment, final String explanation, 
		final String initialPeriod, final String finalPeriod, final String income,final String moreInfo,final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my delors");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, subject);
		super.checkColumnHasValue(recordIndex, 1, item);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("subject", subject);
		super.checkInputBoxHasValue("keylet", keylet);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("explanation", explanation);
		super.checkInputBoxHasValue("initialPeriod", initialPeriod);
		super.checkInputBoxHasValue("finalPeriod", finalPeriod);
		super.checkInputBoxHasValue("income", income);
		super.checkInputBoxHasValue("itemName", item);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		
		super.signOut();
	}
	
	@Test
	@Order(10)
	public void hackingTest() {
		super.navigate("/chef/delor/list");
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
