package acme.testing.chef.delor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefDelorCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/delor/create-delor-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(4)
	public void positiveChefDelorCreateTest(final int recordIndex, final String key, final String subject,
		final String initialPeriod, final String finalPeriod, final String explanation, final String income, final String moreInfo, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "Create a delor");
	   
		super.checkFormExists();
		
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("initialPeriod", initialPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("income", income);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Chef", "List my delors");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, subject);
		super.checkColumnHasValue(recordIndex, 1, item);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("subject", subject);
		super.checkInputBoxHasValue("initialPeriod", initialPeriod);
		super.checkInputBoxHasValue("finalPeriod", finalPeriod);
		super.checkInputBoxHasValue("explanation", explanation);
		super.checkInputBoxHasValue("income", income);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("itemName", item);
			
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/delor/create-delor-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(3)
	public void negativeChefDelorCreateTest(final int recordIndex, final String key, final String subject,
		final String initialPeriod, final String finalPeriod, final String explanation, final String income, final String moreInfo, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "Create a delor");
		
		
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("initialPeriod", initialPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("income", income);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Create");

		super.checkErrorsExist();
		super.signOut();
	}

	@Test
	@Order(10)
	public void hackingTest() {
		super.navigate("/chef/delor/create");
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
