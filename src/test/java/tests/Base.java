package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.TestNG;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.yaml.snakeyaml.Yaml;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.Region;

public class Base extends Config {

	protected Eyes eyes = new Eyes();
	protected int num = 1;
	protected String os;
	protected String browser;
	protected String version;
	protected String device;
	protected int width;
	protected int height;
	protected Platform platform;
	protected SafariOptions saf_options;
	protected String gridUrl;
	
	@BeforeSuite
	public void configureTests() throws IOException {
		loadYaml();
	}
	
	@Parameters({"pbrowser", "pversion", "purl", "pos", "pdevice"})
	@BeforeTest(alwaysRun = true)
	public void setUp(String pbrowser, String pversion, String purl, String pos, @Optional("optional value") String pdevice) throws MalformedURLException {
		//capabilities = new DesiredCapabilities();
		gridUrl = String.format(purl);
		
        // This is your api key, make sure you use it in all your tests.
        eyes.setApiKey("95dlJqlySCR2I46tb9J0HBHdlQjAq5JSN66Y4BcmQig110"); //<-- BA gmail account
	}
	
	public static String getUrl() {
		return base_url;
	}
	
	protected Platform setPlatform(String os) {
		if (os.equalsIgnoreCase("mac")) {
			platform = Platform.MAC;
			return platform;
		}
		else if (os.equalsIgnoreCase("ios")) {
			System.out.println("This test is on an ios simulator");
			platform = Platform.MAC;
			return platform;
		}
		else if (os.equalsIgnoreCase("windows")) {
			platform = Platform.WINDOWS;
			return platform;
		}
		else if (os.equalsIgnoreCase("win7")) {
			platform = Platform.WINDOWS;
			return platform;
		}
		else if (os.equalsIgnoreCase("win10")) {
			System.out.println("Windows 10 tests");
			platform = Platform.WINDOWS;
			//platform = Platform.WIN10;
			return platform;
		}
		else if (os.equalsIgnoreCase("android")) {
			System.out.println("This test is on Android");
			platform = Platform.ANDROID;
			return platform;
		}
		else {
			System.out.println("This test did not have an applicable OS");
			platform = Platform.MAC;
			return platform;
		}
	}
	
	public void refresh(WebDriver drive) {
		drive.navigate().refresh();
	}
	
	public void checkRegWithShift(Eyes eyes, WebElement element, int heightShift, String testName) {
		int top = element.getLocation().getY();
		int left = element.getLocation().getX();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		Region region = new Region(left,top + heightShift,width,height);
		eyes.checkRegion(region);
	}
	
	public void checkRegWithLeftShift(Eyes eyes, WebElement element, int leftShift, String testName) {
		int top = element.getLocation().getY();
		int left = element.getLocation().getX();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		Region region = new Region(left + leftShift,top,width,height);
		eyes.checkRegion(region);
	}
	
	public void checkRegWithShiftAndWait(Eyes eyes, WebElement element, int heightShift, String testName, int wait) {
		int top = element.getLocation().getY();
		int left = element.getLocation().getX();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		Region region = new Region(left,top + heightShift,width,height);
		eyes.checkRegion(region);
	}
}
