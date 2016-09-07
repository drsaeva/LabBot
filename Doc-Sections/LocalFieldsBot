/**
 * Class LocalFieldsBot - parses user name from EHARS,
 * 	iterates through each user drop down menu in the 
 * 	Local Fields section of a lab doc and selects the 
 * 	correct user from that menu
 * 
 * @author dsaeva
 */
 
package labBot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LocalFieldsBot extends LocalFields {

	public LocalFieldsBot(WebDriver driver, LabDocScanner scanner) {
		
		//get user name from EHARS, generate LabDocData object
		parseUser(driver);
		
		//iterate through local fields drop down menu, find and select user
		for (int i=0; i<4; i++) {
			try {
				Select findUser = new Select(driver.findElement(By.cssSelector("select[name='"+localFieldsClassNames.get(i)+"']")));
				findUser.selectByValue(scanner.getValue("localFieldsUser"));
			} catch (Exception e) {
				System.out.println("Not in this selection dropdown, trying next.");
			}
		}
	}
	
}
