package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority=1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountpageTitle();
		System.out.println("Accounts page title is : "+ actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void accPageHeaderTest() {
		String actHeader = accountsPage.getAccountpageHeader();
		System.out.println("Accounts page Header is : "+ actHeader);
		Assert.assertEquals(actHeader, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test(priority=4)
	public void accPageSectionTest() {
		List<String> secList = accountsPage.getAccountSecList();
		Assert.assertEquals(secList, Constants.getExpectedAccSecList());
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook"},
			{"Apple"},
			{"Samsung"},
		};
	}
	
	@Test(priority=5, dataProvider = "productData")
	public void searchTest(String prodName) {
		searchResultsPage = accountsPage.doSearch(prodName);
		Assert.assertTrue(searchResultsPage.getProductsListCount()>0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Apple","Search - Apple"},
		};
	}
	
	@Test(priority=6, dataProvider = "productSelectData")
	public void selectProductTest(String prodName, String mainProdName) {
		searchResultsPage = accountsPage.doSearch(prodName);
		productInfoPage = searchResultsPage.selectProduct(mainProdName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProdName);
	}

}
