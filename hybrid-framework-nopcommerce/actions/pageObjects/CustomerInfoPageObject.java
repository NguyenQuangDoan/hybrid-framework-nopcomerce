package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInfoPageUI;
import pageUIs.LoginPageUI;
import pageUIs.RegisterPageUI;

public class CustomerInfoPageObject extends BasePage{
	
	private WebDriver driver;

	public CustomerInfoPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getEnailAddressTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_ADDRESS_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.EMAIL_ADDRESS_TEXTBOX, "value");
	}

	
}
