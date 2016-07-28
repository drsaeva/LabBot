/**
 *
 * This class provides static methods used in the automated data entry process. Methods will be added
 *      as needed.
 *
 */
 
package labBot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DataBot {

	public static void fillTextField(WebDriver driver, String variableHtmlId, String data) {
		driver.findElement(By.id(variableHtmlId)).sendKeys(data);
	}
	
	public static void selectOption(WebDriver driver, String selectorHtmlId, String optionValue) {
		Select selectOption = new Select(driver.findElement(By.id(selectorHtmlId)));
		selectOption.selectByValue(optionValue);
	}
	
}
