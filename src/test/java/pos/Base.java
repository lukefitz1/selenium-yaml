package pos;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

public class Base {

	protected WebDriver driver;
	protected String baseUrl;
	protected String base_tests_file;
	protected String client_tests_file;
	
	public Base(WebDriver driver) {
		this.driver = driver;
	}

	public String getBaseUrl() throws IOException {
		String url = tests.Base.getUrl();
		return url;
	}
	
	/*public String getBaseUrl() {
		if (System.getProperty("env").equalsIgnoreCase("local")) {
			return "http://mage.dev/";
		}
		else if (System.getProperty("env").equalsIgnoreCase("stage")) {
			return "http://marco.dev/";		
		}
		else {
			return "http://marco.dev/";
		}
	}*/
	
	public void visit(String url) throws IOException {
		if (url.contains("http")) {
			driver.get(url);
		} 
		else {
			baseUrl = getBaseUrl();
			driver.get(baseUrl + url);
		}
	}
	
	public WebElement find(By locator) {
		return driver.findElement (locator);
	}
	
	public void click(By locator) {
		find(locator).click();
	}
	
	public void type(String inputText, By locator) {
		find(locator).sendKeys(inputText);
	}
	
	public void submit(By locator) {
		find(locator).submit();
	}
	
	public String getText(By locator) {
		return find(locator).getText();
	}
	
	public void clearField(By locator) {
		find(locator).clear();
	}
	
	public Boolean isDisplayed(By locator) {
		try {
			return find(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException exception) {
			return false;
		}
	}
	
	public Boolean waitForIsDisplayed(By locator, Integer... timeout) {
		try {
			waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeout.length > 0 ? timeout[0] : null));
		} catch (org.openqa.selenium.TimeoutException exception) {
			return false;
		}
		return true;
	}
	
	public Boolean waitForIsNotDisplayed(By locator, Integer timeout) {
		timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (org.openqa.selenium.TimeoutException exception) {
			return false;
		}
		return true;
		
	}
	
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
		timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(condition);
	}
	
	public void waitForAlert(Integer timeout) {
		timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void closeAlert() {
		driver.switchTo().alert().dismiss();
	}
	
	public Boolean isPresent(By locator) {
		if (driver.findElements(locator).size() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkUrl(String url1, String url2) {
		if (url1.equalsIgnoreCase(url2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int count(By locator) {
		return driver.findElements(locator).size();
	}
}
