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
				if (dataSourceType == "zipCode") {
					DataBot.fillTextField(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
					driver.findElement(By.cssSelector("input[id='"+scanner.getFieldId(dataSourceType)+"']")).sendKeys(Keys.TAB);
				} 
				
				else if (dataSourceType == "cityName") {
					try { 
						selectCity(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
					} catch (Exception unlistedCity) {
						System.out.println("City not found in dropdown choices, selecting default choice for zip code.");
						Actions pressEnter = new Actions(driver);
						pressEnter.sendKeys(Keys.ENTER).build().perform();
					}
				}
				
				else if (dataSourceType == "id") {
					if (scanner.getDataFromHashMap("SSN") != null) {
						//driver.findElement(By.cssSelector("select[id='"+scanner.getFieldId(getOptionValueForIdType("SSN"))+"']"));
						DataBot.selectOption(driver, scanner.getFieldId("ssnSelect"), scanner.getValue("ssnSelect"));
						driver.findElement(By.id(scanner.getFieldId("SSN"))).sendKeys(scanner.getValue("SSN"));
						driver.findElement(By.cssSelector("input[value='addID']")).click();
					}
				}
				
				else {
					//System.out.println(getValue(scanner,dataSourceType));
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
		driver.findElement(By.cssSelector("input[value='Add Name']")).click();
		driver.findElement(By.cssSelector("input[value='Add Address']")).click();
		
	}
	
	private void selectCity(WebDriver driver, String cityNameHtmlId, String cityName) {
		Select selectOption = new Select(driver.findElement(By.id(cityNameHtmlId)));
		selectOption.selectByVisibleText(cityName);
	}
	
}
