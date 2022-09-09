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

package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/update-pimpam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String key, final String titulo,
		final String periodoInicial, final String periodoFinal, final String descripcion, final String presupuesto, final String enlace, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("titulo", titulo);
		super.fillInputBoxIn("periodoInicial", periodoInicial);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("periodoFinal", periodoFinal);
		super.fillInputBoxIn("presupuesto", presupuesto);
		super.fillInputBoxIn("enlace", enlace);
		
		super.clickOnSubmit("Update");
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);

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
	@CsvFileSource(resources = "/chef/pimpam/update-pimpam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(5)
	public void negativeTest(final int recordIndex, final String key, final String titulo,
		final String periodoInicial, final String periodoFinal, final String descripcion, final String presupuesto, final String enlace, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("titulo", titulo);
		super.fillInputBoxIn("periodoInicial", periodoInicial);
		super.fillInputBoxIn("periodoFinal", periodoFinal);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("presupuesto", presupuesto);
		super.fillInputBoxIn("enlace", enlace);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();

		super.signOut();
	}
}
