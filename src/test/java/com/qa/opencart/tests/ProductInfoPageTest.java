package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority = 1)
	public void prodHeaderTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(),"MacBook Pro");
	}
	
	@Test(priority = 2) 
	public void productImgesCountTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.MACBOOKPRO_IMAGE_COUNT);
	}
	
	@Test(priority = 3)
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfoMap.get("ExTaxPrice"), "Ex Tax: $2,000.00");
		softAssert.assertEquals(actProductInfoMap.get("Price"), "$2,000.00");
		softAssert.assertAll();
//		hard assertiion means the program will fail whenever there are multiple assertions
//		in Soft Assertion if one assertion fails it will execute the next assertions
	}
}
