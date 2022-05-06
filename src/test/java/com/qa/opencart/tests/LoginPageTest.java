package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100:Design Open cart app - Login page")
@Story("Epic 100:Design Open cart app - Login page")
public class LoginPageTest extends BaseTest {

	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("page Title is : "+ actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("login page url test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
		
	}
	@Description("forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	@Description("Registration link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void isRegisterlinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	@Description("login test with correct creds")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountpageTitle(), Constants.ACCOUNT_PAGE_TITLE);
		
	}
	
	
}
