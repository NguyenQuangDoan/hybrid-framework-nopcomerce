package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInfoPageUI;
import pageUIs.LoginPageUI;
import pageUIs.RegisterPageUI;

public class AddressPageObject extends BasePage{
	
	private WebDriver driver;

	public AddressPageObject(WebDriver _driver) {
		this.driver = _driver;
	}


}
