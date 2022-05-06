package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	
	private ElementUtil eleUtil;
	
	private By productHeaderName = By.xpath("//div[@id='content']//h1");
	private By prodImages = By.cssSelector("ul.thumbnails img");
	private By prodMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By prodPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader(){
		String prodHeaderName = eleUtil.doGetText(productHeaderName);
		System.out.println("product header name is : "+ prodHeaderName);
		return prodHeaderName;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(prodImages, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductMetaPriceData();
		return productInfoMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(prodMetaData);
		for(WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductMetaPriceData() {
		List<WebElement> metaDataPriceList = eleUtil.getElements(prodPriceData);
		String price = metaDataPriceList.get(0).getText().trim();
		String exPrice = metaDataPriceList.get(1).getText().trim();
		productInfoMap.put("Price", price);
		productInfoMap.put("ExTaxPrice", exPrice);
	} 
}
