package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By prodResults = By.cssSelector("div.caption a");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getProductsListCount() {
		int resultCount = eleUtil.waitForElementsToBeVisible(prodResults, Constants.DEFAULT_TIME_OUT).size();
		System.out.println("the product result count is : "+ resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String mainProdName) {
		System.out.println("main product name is : "+ mainProdName);
		List<WebElement> searchList = eleUtil.waitForElementsToBeVisible(prodResults, Constants.DEFAULT_TIME_OUT);
		for(WebElement e: searchList) {
			String text = e.getText();
			if(text.equals(mainProdName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	
}
