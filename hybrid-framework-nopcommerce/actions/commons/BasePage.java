package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.AddressPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.OrderPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RewardPointPageObject;
import pageUIs.BasePageUI;
import pageUIs.CustomerInfoPageUI;

public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		waitForAlertPresence(driver).sendKeys(value);
	}

	public void switchToWindowByID(WebDriver driver, String windowPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(windowPageID)) {
				driver.switchTo().window(window);
			}
		}
	}

	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			sleepInSecond(2);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentPageID)) {
				driver.switchTo().window(window);
				sleepInSecond(1);
				driver.close();
			}
		}
		driver.switchTo().window(parentPageID);
		sleepInSecond(1);
	}


	public By getByXpath(String xpathEpression) {
		return By.xpath(xpathEpression);
	}

	public WebElement getWebElement(WebDriver driver, String xpathEpression) {
		return driver.findElement(getByXpath(xpathEpression));
	}

	public List<WebElement> getWebElements(WebDriver driver, String xpathEpression) {
		return driver.findElements(getByXpath(xpathEpression));
	}

	public void clickToElement(WebDriver driver, String xpathEpression) {
		getWebElement(driver, xpathEpression).click();
	}

	public void sendkeyToElement(WebDriver driver, String xpathEpression, String value) {
		getWebElement(driver, xpathEpression).clear();
		getWebElement(driver, xpathEpression).sendKeys(value);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathEpression, String itemText) {
		new Select(getWebElement(driver, xpathEpression)).selectByVisibleText(itemText);
	}

	public String getSelectedTextInDefaultDropdown(WebDriver driver, String xpathEpression) {
		return new Select(getWebElement(driver, xpathEpression)).getFirstSelectedOption().getText();
	}

	public boolean isDefaultDropdownMultiple(WebDriver driver, String xpathEpression) {
		return new Select(getWebElement(driver, xpathEpression)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementText(WebDriver driver, String xpathEpression) {
		return getWebElement(driver, xpathEpression).getText();
	}

	public String getElementAttribute(WebDriver driver, String xpathEpression, String attributeName) {
		return getWebElement(driver, xpathEpression).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String xpathEpression, String propertyName) {
		return getWebElement(driver, xpathEpression).getCssValue(propertyName);
	}

	public String getHexaColorByRgbaColor(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}

	public int getElementsNumber(WebDriver driver, String xpathEpression) {
		return getWebElements(driver, xpathEpression).size();
	}

	public void checkToRadioOrCheckbox(WebDriver driver, String xpathEpression) {
		if (!getWebElement(driver, xpathEpression).isSelected()) {
			getWebElement(driver, xpathEpression).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String xpathEpression) {
		if (getWebElement(driver, xpathEpression).isSelected()) {
			getWebElement(driver, xpathEpression).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathEpression) {
		return getWebElement(driver, xpathEpression).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathEpression) {
		return getWebElement(driver, xpathEpression).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathEpression) {
		return getWebElement(driver, xpathEpression).isSelected();
	}

	public void switchToFrame(WebDriver driver, String xpathEpression) {
		driver.switchTo().frame(getWebElement(driver, xpathEpression));
	}

	public void switchToDefaultContentPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathEpression) {
		new Actions(driver).moveToElement(getWebElement(driver, xpathEpression)).perform();
	}

	public void pressKeyboardToElement(WebDriver driver, String xpathEpression, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, xpathEpression), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String xpathEpression) {
		WebElement element = getWebElement(driver, xpathEpression);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathEpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathEpression));
	}

	public void scrollToElement(WebDriver driver, String xpathEpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, xpathEpression));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathEpression, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getWebElement(driver, xpathEpression));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathEpression, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xpathEpression));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathEpression) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xpathEpression));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathEpression) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathEpression));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String xpathEpression) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathEpression)));
	}	
	
	public void waitForElementClickable(WebDriver driver, String xpathEpression) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathEpression)));
	}	
	
	public void waitForElementInvisible(WebDriver driver, String xpathEpression) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathEpression)));
	}	
	
	public void waitForAllElementVisible(WebDriver driver, String xpathEpression) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathEpression)));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String xpathEpression) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, xpathEpression)));
	}	

	public OrderPageObject openOrderPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ORDERS_LINK);
		clickToElement(driver, BasePageUI.ORDERS_LINK);
		return PageGeneratorManager.getOrderPage(driver);
	}
	
	public AddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getAddressPage(driver);
	}
	
	public RewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getRewardPointPage(driver);
	}
	
	public CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}
	
	
 	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sleepInMillisecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private long shortTimeOut = 5;
	private long longTimeOut = 30;
}
