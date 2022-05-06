package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// 1.declare private constructer
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2.page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3.By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	
//	4.Page Actions
	@Step("Getting login page Title..")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	@Step("Getting login page url..")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	@Step("forgot password link exist..")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	@Step("register link Title..")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	@Step("login with correct creds: username:{0} and password:{1}")
	public AccountsPage doLogin(String un, String pw) {
		System.out.println("login with : " + un +" " + pw);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pw);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	@Step("login with wrong creds: username:{0} and password:{1}")
	public boolean doLoginWithWrongCredentials(String un, String pw) {
		System.out.println("login with wrong credentials : " + un +" " + pw);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pw);
		eleUtil.doClick(loginBtn);
		String errorMessage = eleUtil.doGetText(loginErrorMsg);
		System.out.println(errorMessage);
		
		if(errorMessage.contains(Constants.LOGIN_ERROR_MESSAGE)) {
			System.out.println("Login is not successfull...");
			return false;
		}
		return true;
	}
	@Step("going to registration page")
	public RegistrationPage goToRegistrationPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}

}
