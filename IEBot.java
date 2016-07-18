/**
 * IEBot: 
 * basic IEDriver initialization and control 
 * 
 * @author dsaeva
 *
 */

package labBot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class IEBot extends IEBotSuper {
	
	//test eHARS url
	public static final String url = new String(
			"http://10.197.67.14/ehars/jsp/common/loginDeterminationAction.do");
	
	public IEBot(){
		
		IEBot ieBot = new IEBot();
		DesiredCapabilities desCaps = DesiredCapabilities.internetExplorer();
		ieBot.getIEDesCaps(desCaps);
		
		WebDriver driver = new InternetExplorerDriver(desCaps);
		ieBot.logMeIn(driver);
		
		driver.close();
	}
	
	/**
	 * logMeIn will deprecate late --> notify user to login and wait (timer/sleep)
	 */
	
	private void logMeIn(WebDriver driver) {											    
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,"0"));
		driver.get(url);
		driver.findElement(By.id("userId")).sendKeys("x");								
		driver.findElement(By.id("userPassword")).sendKeys("x");
		driver.findElement(By.id("userPassword")).submit();
		
	}
