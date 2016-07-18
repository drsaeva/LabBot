/**
 * Superclass IEBotSuper: 
 * encapsulates necessary IEDriver run parameters
 * parameters are later retrieved by subclass IEBot as needed
 * see IEBot for usage
 * 
 * @author dsaeva
 *
 */

package labBot;

import org.openqa.selenium.remote.DesiredCapabilities;

public class IEBotSuper {	
	
	//superclass constructor to force IEDriver path, generate new desCaps object
	public IEBotSuper() {
		
		System.setProperty("webdriver.ie.driver", "E:/IEDriverServer_Win32_2.53.1/IEDriverServer.exe");
		
	}
	
	//access to desCaps for IEBot subclass
	public DesiredCapabilities getIEDesCaps(DesiredCapabilities desCaps) {
		
		return desCaps; 
		
	}
	
	/**
	 * logMeIn will deprecate late --> notify user to login and wait (timer/sleep)
	 */
	
	//establishes IE webdriver run params	
	public void setIEDesCaps(DesiredCapabilities desCaps) {         					                 
		
		desCaps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		desCaps.setCapability("EnableNativeEvents", false);
		desCaps.setCapability("ignoreZoomSetting", true);
		desCaps.setJavascriptEnabled(true);
		
	}

}
