/**
 * 
 * This class serves as a controller for automated data-entry of data tied to the Form Info section
 * 	of a lab doc. It extends the FormInfo class, which instantiates LabDocData objects for unchanging
 * 	variables and provides an associative framework to which related LabDocData objects generated via
 * 	Scanner classes can be tied and called during automation.
 * 
 */
 
package labBot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FormInfoBot extends FormInfo {

	public FormInfoBot(WebDriver driver) {
		 
		for (String dataSourceType : dataInFormInfo) {
			if (scanner.getDataFromHashMap(dataSourceType) != null) {
				try { 
					if (driver.findElements(By.cssSelector("input[id='"+getFieldId(scanner,dataSourceType)+"']")).size() != 0) {
						DataBot.fillTextField(driver, getFieldId(scanner,dataSourceType), getValue(scanner, dataSourceType));
					}
				} catch (Exception e) {	
				}
				
				try { 
					if (driver.findElements(By.cssSelector("selector[id='"+getFieldId(scanner,dataSourceType)+"']")).size() != 0) {
						DataBot.selectOption(driver, getFieldId(scanner,dataSourceType), getValue(scanner, dataSourceType));
					}
				} catch (Exception e) {
				}
			}
		}
		
	}
	
	private String getValue(LabDocScanner scanner, String dataSourceType) {
		return scanner.getDataFromHashMap(dataSourceType).getDataValue();
	}
	
	private String getFieldId(LabDocScanner scanner, String dataSourceType) {
		return scanner.getDataFromHashMap(dataSourceType).getDataFieldHtmlId();
	}

}
