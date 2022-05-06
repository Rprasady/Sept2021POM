package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegetiveTest extends BaseTest{

	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			{"testsfjk@gmail.com", "jfdsdj@1"},
			{"", "testing21"},
			{"TESTING@gmail.com", ""},
			{"", ""}
		};
	}
	
	
	@Test(dataProvider = "loginWrongTestData")
	public void loginNegetiveTest(String un, String pw) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(un, pw));
	}
	
}
