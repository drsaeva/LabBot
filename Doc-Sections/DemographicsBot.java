package labBot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DemographicsBot extends Demographics {

	public DemographicsBot(WebDriver driver, QuestVaScanner scanner) {
		for (String dataSourceType : dataInDemographics) {
			if (scanner.getDataFromHashMap(dataSourceType) != null) {
				
				if (driver.findElements(By.cssSelector("input[id='"+scanner.getFieldId(dataSourceType)+"']")).size() != 0) {
					DataBot.fillTextField(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
				} 
					
				if (driver.findElements(By.cssSelector("select[id='"+scanner.getFieldId(dataSourceType)+"']")).size() != 0) {
					DataBot.selectOption(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
				}		
			}
		}
	}

}
