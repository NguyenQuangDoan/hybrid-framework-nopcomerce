package commons;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	/*Web Browser*/
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
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
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
		for(String window : allWindows) {
			if(!window.equals(windowPageID)) {
				driver.switchTo().window(window);
			}
		}
	}
	
	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String window : allWindows) {
			driver.switchTo().window(window);
			sleepInSecond(2);
			String actualPageTitle = driver.getTitle();
			if(actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(WebDriver driver, String parentPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String window : allWindows) {
			if(!window.equals(parentPageID)) {
				driver.switchTo().window(window);
				sleepInSecond(1);
				driver.close();
			}
		}
		driver.switchTo().window(parentPageID);
		sleepInSecond(1);
	}
	
	
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
