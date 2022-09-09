/*
 * EmployerJobUpdateTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.chef.delor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefDelorUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/delor/update-delor-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String key, final String subject,
		final String initialPeriod, final String finalPeriod, final String explanation, final String income, final String moreInfo, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my delors");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("initialPeriod", initialPeriod);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("income", income);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Update");
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);

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
	@CsvFileSource(resources = "/chef/delor/update-delor-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(5)
	public void negativeTest(final int recordIndex, final String key, final String subject,
		final String initialPeriod, final String finalPeriod, final String explanation, final String income, final String moreInfo, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my delors");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("initialPeriod", initialPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("income", income);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();

		super.signOut();
	}
}
