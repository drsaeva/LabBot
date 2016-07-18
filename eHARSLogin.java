package LabBot;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import com.google.common.base.Function;


public class eHARSLogin {
	
	public static final File file = new File(
			"E:/IEDriverServer_Win32_2.53.1/IEDriverServer.exe");						//path to IEDriver on USB stick
	public static final DesiredCapabilities desCaps = 
			DesiredCapabilities.internetExplorer();										//new desired capabilities object to set IEDriver run params
	public static final String url = new String(										//test eHARS url
			"http://10.197.67.14/ehars/jsp/common/loginDeterminationAction.do");
	
	public File labDoc = new File("needspath");
	
	
	public static void main(String[] args) {
		

		System.setProperty("webdriver.ie.driver", file.getAbsolutePath()); 				 //force IEDriver path
		eHARSLogin login = new eHARSLogin();											 //instantiate new login object
		login.setIEDesCaps(desCaps); 										 			 //run cap setter method
		WebDriver driver = new InternetExplorerDriver(desCaps);							 //instantiate new IEDriver											 
		login.logMeIn(driver);															 //access test eHARS url and login - see method for more details

		driver.findElement(By.linkText("Search a Document")).click();					 //navigate to search - unnecessary right after login

		driver.close();
	}
	
	
	/**
	 * logMeIn will deprecate later --> notify user to login and wait (timer/sleep)
	 */
	
	private void logMeIn(WebDriver driver) {											    
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,"0"));  	 //set screen zoom to 100% to resolve webdriver errors
		driver.get(url); 																	 //navigate to url
		driver.findElement(By.id("userId")).sendKeys("dsaeva");								
		driver.findElement(By.id("userPassword")).sendKeys("Password1");
		driver.findElement(By.id("userPassword")).submit();
	}
	
	private static WebElement waitJS(final WebDriver driver, final By locator) {			 //webdriver fluentwait for JQuery elements
	    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(5, TimeUnit.SECONDS)
	            .pollingEvery(500, TimeUnit.MILLISECONDS)
	            .ignoring(NoSuchElementException.class);
	    return wait.until(presenceOf(locator));
	}
	
	private static Function<WebDriver,WebElement> presenceOf(final By locator) {
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		};
	}
	
	
	public void setIEDesCaps(DesiredCapabilities desCaps) {         					 //setter method to establish IE webdriver run params                
		
		desCaps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		desCaps.setCapability("EnableNativeEvents", false);
		desCaps.setCapability("ignoreZoomSetting", true);
		desCaps.setJavascriptEnabled(true);
		
	}
	
}
