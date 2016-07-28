/**
 * 
 * This class provides a static method for generating FluentWaits to allow Selenium to process
 *      elements hidden by JQuery/JS scripting after the unlocking action has been peformed.
 *
 */

package labBot;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class FluentWaitForJS {

	public static WebElement waitJS(final WebDriver driver, final By locator) {			 //webdriver fluentwait for JS/JQuery elements
	    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(5, TimeUnit.SECONDS)
	            .pollingEvery(500, TimeUnit.MILLISECONDS)
	            .ignoring(NoSuchElementException.class);
	    return wait.until(presenceOf(locator));
	}
	
	public static Function<WebDriver,WebElement> presenceOf(final By locator) {
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		};
	}
	
	
	
}
