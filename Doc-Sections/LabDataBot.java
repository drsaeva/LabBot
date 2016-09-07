/**
 * labBot - automation class for the LabData section of a LabDoc
 * automates entry of associated data stored in LabDocData objects in EHARS
 * prompts user for input in instances that Selenium has difficulty automating
 * 
 * @author drsaeva
 */
package labBot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LabDataBot extends LabData {

	private String userInputOnFacility;
	private String userInputOnProvider;
	
	//constructor for LabDataBot, iterates over dataInLabData and enters each related data item into its respective field
	public LabDataBot(WebDriver driver, LabDocScanner scanner) {
		for (String dataSourceType : dataInLabData) {
			if (scanner.getDataFromHashMap(dataSourceType) != null) {
				
				//find facility based on parsed value, prompt user to select the appropriate option if available and provide input on that
				if (dataSourceType == "facilityName") {
					fieldNameSearch(driver, scanner, dataSourceType, "Facility", userInputOnFacility);
				}
				
				//find provider based on parsed value, prompt user to select the appropriate option if available and provide input on that
				if (dataSourceType == "physicianFirstName") {
					fieldNameSearch(driver, scanner, dataSourceType, "Provider", userInputOnProvider);
				}
				
				//enters lab test type as well as the proper data value depending on the type
				//TODO need code for Ag/Ab qual test as well as type-differentiating immunoasssay
				if (dataSourceType == "testType") {
					DataBot.selectOption(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
					
					// for CD4 values
					if (scanner.getValue("testType") == "CD4 COUNT") {
						
						if (scanner.getDataFromHashMap("cells/uL") != null) {
							System.out.println(scanner.getDataFromHashMap("cells/uL").getDataValue());
							DataBot.fillTextField(driver, scanner.getFieldId("cells/uL"), scanner.getValue("cells/uL"));
							driver.findElement(By.cssSelector("input[value='Add Lab']")).click();
						}
						
						if (scanner.getDataFromHashMap("%") != null) {
							System.out.println(scanner.getDataFromHashMap("%").getDataValue());
							DataBot.fillTextField(driver, scanner.getFieldId("%"), scanner.getValue("%"));
							driver.findElement(By.cssSelector("input[value='Add Lab']")).click();
						}
						
					}
					// for RNA quant counts
					if (scanner.getValue("testType").contains("RNA")) {
						System.out.println(scanner.getDataFromHashMap("copies/mL").getDataValue());
						DataBot.fillTextField(driver, scanner.getFieldId("copies/mL"), scanner.getValue("copies/mL"));
						driver.findElement(By.cssSelector("input[value='Add Lab']")).click();
					}
				}
				
				//generic data entry for unspecified data types not described above but listed in dataInLabData
				if (driver.findElements(By.cssSelector("input[id='"+scanner.getFieldId(dataSourceType)+"']")).size() != 0) {
					DataBot.fillTextField(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
				} 	
			}
		}
		
		//tell the user to select a Lab source name from the drop down, pause webdriver
		//  while not selected and break if no selection is made within the timeframe
		NotifyUser.selectLabName();
		do {} while (driver.findElement(By.cssSelector("option[value='']")).getAttribute("value").isEmpty());
		
	}
	
	private void fieldNameSearch(WebDriver driver, LabDocScanner scanner, String dataSourceType, String facilityOrProvider, String userInput) {
		DataBot.fillTextField(driver, scanner.getFieldId(dataSourceType), getSubstring(scanner.getValue(dataSourceType)));
		driver.findElement(By.cssSelector(
				"input[onclick*='labReportForm"+facilityOrProvider+"Search']")).click();
		NotifyUser.findSearchResult(userInput, facilityOrProvider);
		System.out.println(userInput);
		do {} while(driver.findElement(By.cssSelector("input[id='"+facilityOrProvider.toLowerCase()+"Uid']"))
				.getAttribute("value").isEmpty() && userInput == null); 
		if (userInput == "Not Found") {
			NotifyUser.searchResultNotFound(facilityOrProvider);
			driver.findElement(By.cssSelector("input[onclick*='labReportForm"+facilityOrProvider+"Clear']")).click();
		}
	}
	
	private String getSubstring(String name) {
		Pattern substring = Pattern.compile("([A-Z]+)\\s");
		Matcher matchSubstring = substring.matcher(name);
		if (matchSubstring.find()) {
			return matchSubstring.group(1);
		}
		else {
			return null;
		}
	}

}

