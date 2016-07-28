/**
 * 
 * This class reresents a section of a lab doc entry into eHARS, and is instantiated upon identification of 
 * 	an instantiated LabDocData object keyed to it (ie: dateComplete).
 * This relationship is mapped in an automation class to facilitate automated data entry into eHARS
 * 
 */

package labBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormInfo extends LabDocSection {
	
	protected String dateReceived, isNewCase, dateComplete;
	protected static final Set<String> dataInIdentification = ImmutableSet.of(
			"dateReceived", "isNewCase", "dateComplete"
			);
	
	protected final String reportingCity = "BALTIMORE"; 	//Always Baltimore
	protected final String reportingCityHtmlKeyword = "repHlthDeptName";
	
	protected final String labSource = "A05.03";  		//Default: private lab; make setter in the event that other labs are to be processed
	
	protected final String reportMedium = "2";  			//Select Code for Paper medium
	
	protected final String surveillanceMethod = "P"; 		//Default: passive (P); make setter in the event that there should be another option
	
	protected final String sectionHtmlKeyword = "formInfoForm";
	protected static final String sectionName = new String("FormInfo");
	
	protected static void mapFormInfoSection(HashMap<String, LabDocSection> sectionsInLabDoc) {
		sectionsInLabDoc.put(sectionName, new FormInfo());
	}
	
	public FormInfo() {
		// TODO Examine code - do I need anything else aside from these methods being run?
		setNewCase(isNewCase);
		setDateComplete(dateComplete);
		setDateComplete(dateReceived);
		
	}
	
	public String getNewCase() {
		return newCase;
	}
	
	public void setNewCase() {
		// TODO code to set new case Y/N answer
		/*
		 * Requirements - regex for case name   <--- set via Identification Parsing Object
		 * 		- search for case name  <--- results likely set via SearchCase Object
		 * 			* needs - multiple methods of searching (DOB + fullname, DOB + L or F name, SSN, etc)
		 * 				- search logic: if DOB+FN fails, do alternate search; if all fail, new case 
		 */
	}
	
	public String getDateComplete() {
		return dateComplete;
	}
	
	protected void setDateComplete(String dateComplete) {
		// TODO code to lab complete/report date
		/*
		 * Requirements - regex for complete date
		 * 				- this should be fairly simple
		 */
		
		
		dateComplete = "......"; //default behavior until regex is coded
		this.dateComplete = dateComplete;
	}
	
	public String getSurveillanceMethod() {
		return surveillanceMethod;
	}
	
	public String getReportMedium() {
		return reportMedium;
	}
	
	public String getReportingCity() {
		return reportingCity;
	}

	public String getDateReceived() {
		return dateReceived; 
	}
	
	private void setDateReceived(String dateReceived) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //Always today?
		Date today = new Date();
		dateReceived = dateFormat.format(today).toString();
		this.dateReceived = dateReceived;
	}
	
	public String getLabSource() {
		return labSource;
	}

}
