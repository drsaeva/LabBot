package labBot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * This class is currently deprecated. It may be refactored for re-use at a later time.
 * 
 */

public class FormInfoBot extends FormInfo {

	public FormInfoBot(WebDriver driver) {
		/*	TODO Determine what this constructor needs 
		 *  		- currently invokes superclass constructor to generate FormInfo object with field values as properties
		 *  			* is this how I want to do things? 
		 *  			* this means FormInfoBot works as a control Class for Form Info, 
		 *  				rather than a different (global?) controller
		 *  		- eventually will perform automated form-filling operations by calling fillXField() methods
		 *  		- object can still be created by an overseeing controller Class - possible compomise
		 */
		
		super();
		
		FormInfo formInfo = new FormInfo();
		this.fillReportingCityField(driver, formInfo);
		this.fillDateReceivedField(driver, formInfo);
		this.fillSourceField(driver, formInfo);
		this.fillNewCaseField(driver, formInfo);
		this.fillReportMediumField(driver, formInfo);
		this.fillSurveillanceMethodField(driver, formInfo);
		this.fillDateCompleteField(driver, formInfo);
	}
	
	/*
	 * Current methods analysis
	 * 		- method inputs are repetitive - is there a way to simplify this?
	 */
	
	public void fillReportingCityField(WebDriver driver, FormInfo formInfo) {
		driver.findElement(By.name("formInfoForm.repHlthDeptName")).sendKeys(formInfo.getReportingCity());
	}
	
	public void fillDateReceivedField(WebDriver driver, FormInfo formInfo) {
		driver.findElement(By.name("formInfoForm.receiveDt")).sendKeys(formInfo.getDateReceived());
	}
	
	public void fillSourceField(WebDriver driver, FormInfo formInfo) {
		driver.findElement(By.name("formInfoForm.documentSourceCd")).sendKeys(formInfo.getLabSource());
		// TODO get html code for Source field Popup, code behavior to click A05.03 link
		
	}
	
	public void fillNewCaseField(WebDriver driver, FormInfo formInfo) {
		Select isNewCase = new Select(driver.findElement(By.name("formInfoForm.initinvest")));
		isNewCase.selectByValue(formInfo.getNewCase());
	}
	
	public void fillReportMediumField(WebDriver driver, FormInfo formInfo) {
		Select selectReportMedium = new Select(driver.findElement(By.name("formInfoForm.rptMedium")));
		selectReportMedium.selectByValue(formInfo.getReportMedium());
	}
	
	public void fillSurveillanceMethodField(WebDriver driver, FormInfo formInfo) {
		Select selectSurveillanceMethod = new Select(driver.findElement(By.name("formInfoForm.survMethod")));
		selectSurveillanceMethod.selectByValue(formInfo.getSurveillanceMethod());
	}
	
	public void fillDateCompleteField(WebDriver driver, FormInfo formInfo) {
		driver.findElement(By.name("formInfoForm.completeDt")).sendKeys(formInfo.getDateComplete());
	}

}
