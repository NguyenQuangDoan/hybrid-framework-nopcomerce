package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInfoPageUI;
import pageUIs.LoginPageUI;
import pageUIs.RegisterPageUI;

public class RewardPointPageObject extends BasePage{
	
	private WebDriver driver;

	public RewardPointPageObject(WebDriver _driver) {
		this.driver = _driver;
	}


}
