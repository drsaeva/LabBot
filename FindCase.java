/**
 * Crude search algorithm for locating cases with identifiers parsed from 
 * scanned lab docs via eHARS search function. Currently in development,
 * only has basic functionality. To be integrated into controlling class at
 * a later time via instantiation.
 */

package labBot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.google.common.collect.ImmutableMap;

public class FindCase {
	
	private static List<String> dataToBeSearched;
	
	private final LabDocData dobStatus = new LabDocData("dobStatus", "EQ");
	private final LabDocData lastNameStatus = new LabDocData("lastNameStatus", "EQ");
	private final LabDocData firstNameStatus = new LabDocData("firstNameStatus", "EQ");
	
	static {
		ArrayList<String> tempFields = new ArrayList<String>();
		tempFields.add("dob");
		tempFields.add("lastNameStatus");
		tempFields.add("lastName");
		tempFields.add("firstNameStatus");
		tempFields.add("firstName");
		dataToBeSearched = Collections.unmodifiableList(tempFields);
	}
	
	private Map<String,LabDocData> dataStatusObjects = ImmutableMap.<String,LabDocData>builder()
			.put("dobStatus", dobStatus).put("lastNameStatus", lastNameStatus)
			.put("firstNameStatus", firstNameStatus).build();
	
	public FindCase(WebDriver driver, LabDocScanner scanner) {
		tryAllSearchFields(driver, scanner);
		setNewCaseStatus(driver, scanner);
	}
	
	private void tryAllSearchFields(WebDriver driver, LabDocScanner scanner) {
		for (String dataSourceType : dataToBeSearched) {
			if (driver.findElements(By.cssSelector("input[id='"+scanner.getFieldId(dataSourceType)+"']")).size() != 0) {
				DataBot.fillTextField(driver, scanner.getFieldId(dataSourceType), scanner.getValue(dataSourceType));
			} 
			
			if (driver.findElements(By.cssSelector("select[id='"+dataStatusObjects.get(dataSourceType).getDataFieldHtmlId()+"']")).size() != 0) {
				System.out.println(scanner.getValue(dataSourceType));
				DataBot.selectOption(driver, dataStatusObjects.get(dataSourceType).getDataFieldHtmlId(), dataStatusObjects.get(dataSourceType).getDataValue());
			}
		}
		driver.findElement(By.id("firstName")).sendKeys(Keys.ENTER);
	}
	
	private void setNewCaseStatus(WebDriver driver, LabDocScanner scanner) {
		if (driver.findElement(By.xpath("//*[contains(text(),'" + scanner.getValue("lastName") + "')]")) != null) {
			LabDocData.createLabDocData(LabDocScanner.dataInDoc, "isNewCase", "Y");
		} /*
		 TODO this section needs to be edited to occur after several different searches are tried
		 
		else {
			LabDocData.createLabDocData(LabDocScanner.dataInDoc, "isNewCase", "N");
		}*/
		
	}
	
	private void tryBeginsWith(LabDocData data) {
		data.setDataValue("BG");	
	}
	
	private void tryContains(LabDocData data) {
		data.setDataValue("CT");	
	}

	private void returnToSearchPage(WebDriver driver) {
		driver.findElement(By.linkText("Search a Document")).click();
	}
}
