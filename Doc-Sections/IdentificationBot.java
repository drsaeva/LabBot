/**
 * Class IdentificationBot 
 * Iterates over data belonging to the Identification Section (see parent class)
 * Enters child data object values into their respective fields
 * 
 * @author drsaeva
 */

package labBot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class IdentificationBot extends Identification {

	//TODO this needs logic regarding SSN, MedRecNumbers, etc (Id # field)
	
	public IdentificationBot(WebDriver driver, QuestVaScanner scanner) {
		for (String dataSourceType : dataInIdentification) {
			if (scanner.getDataFromHashMap(dataSourceType) != null) {
				
				//handler for zip code - presses TAB after entry to activate frontend JS that fills City/County fields based on entry
				if (dataSourceType == "zipCode") {
					DataBot.fillTextField(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
					driver.findElement(By.cssSelector("input[id='"+scanner.getFieldId(dataSourceType)+"']")).sendKeys(Keys.TAB);
				} 
				
				//handler for city - select city name from available cities for zip code input, if not present notify user and move on
				else if (dataSourceType == "cityName") {
					try { 
						DataBot.selectOptionText(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
					} catch (Exception unlistedCity) {
						System.out.println("City not found in dropdown choices, selecting default choice for zip code.");
						Actions pressEnter = new Actions(driver);
						pressEnter.sendKeys(Keys.ENTER).build().perform();
					}
				}
				
				//handler for SSN - performs selects SSN from ID drop down menu, then enters parsed SSN data if it exists
				else if (dataSourceType == "id") {
					if (scanner.getDataFromHashMap("SSN") != null) {
						DataBot.selectOption(driver, scanner.getFieldId("ssnSelect"), scanner.getValue("ssnSelect"));
						driver.findElement(By.id(scanner.getFieldId("SSN"))).sendKeys(scanner.getValue("SSN"));
						driver.findElement(By.cssSelector("input[value='addID']")).click();
					}
				}
				
				//generic handlers for all other fields - i.e. first/last name, street address
				else {
					if (driver.findElements(By.cssSelector("input[id='"+scanner.getFieldId(dataSourceType)+"']")).size() != 0) {
						System.out.println(scanner.getValue(dataSourceType));
						DataBot.fillTextField(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
					} 
					
					if (driver.findElements(By.cssSelector("select[id='"+scanner.getFieldId(dataSourceType)+"']")).size() != 0) {
						System.out.println(scanner.getValue(dataSourceType));
						DataBot.selectOption(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
					}
					
				}
				
			}

		}
		
		//Click "Add" for name and address sections, finalizing input
		driver.findElement(By.cssSelector("input[value='Add Name']")).click();
		driver.findElement(By.cssSelector("input[value='Add Address']")).click();
		
	}

}
