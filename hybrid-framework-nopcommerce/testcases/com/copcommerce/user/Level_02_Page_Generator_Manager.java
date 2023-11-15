package com.copcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddressPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.OrderPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointPageObject;

public class Level_02_Page_Generator_Manager extends BaseTest {

	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private OrderPageObject orderPage;
	private RewardPointPageObject rewardPointPage;
	private AddressPageObject addressPage;
	private String firstName, lastName, emailAddress, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName = "Doan";
		lastName = "Nguyen";
		emailAddress = "dnq" + generateFakeNumber() + "@gmail.net";
		password = "123456";
	}

	@Test
	public void User_01_Register_To_System() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");
//		registerPage.clickToLogoutLink();
//		homePage = new HomePageObject(driver);
	}

	@Test
	public void User_02_Login_To_System() {
		loginPage = registerPage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
	}

	@Test
	public void User_03_My_Account_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
		Assert.assertEquals(customerInfoPage.getEnailAddressTextboxValue(), emailAddress);
	}
	
	@Test
	public void User_04_Navigate_Page() {
		orderPage = customerInfoPage.openOrderPage(driver);
		
		rewardPointPage = orderPage.openRewardPointPage(driver);
		
		orderPage = rewardPointPage.openOrderPage(driver);
		
		customerInfoPage = orderPage.openCustomerInfoPage(driver);
		 
		addressPage = customerInfoPage.openAddressPage(driver);
		
		rewardPointPage = addressPage.openRewardPointPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
