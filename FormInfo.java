package labBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormInfo extends LabDocSection {
	
	private String reportingCity, dateReceived, labSource,
					newCase, reportMedium, surveillanceMethod,
					dateComplete;
	
	
	public FormInfo() {
		// TODO Examine code - do I need anything else aside from these methods being run?
		this.setReportingCity();
		this.setDateReceived();
		this.setLabSource();
		this.setNewCase();
		this.setReportMedium();
		this.setSurveillanceMethod();
		this.setDateComplete();
		
	}

	@Override
	public void parseLineWithRegex() {
		// TODO Auto-generated method stub

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
	
	public void setDateComplete() {
		// TODO code to lab complete/report date
		/*
		 * Requirements - regex for complete date
		 * 		- this should be fairly simple
		 */
	}
	
	public String getSurveillanceMethod() {
		return surveillanceMethod;
	}
	
	public void setSurveillanceMethod() {
		surveillanceMethod = "PASSIVE"; //Default: passive; update in the event that this is to be different
	}
	
	public String getReportMedium() {
		return reportMedium;
	}
	
	public void setReportMedium() {
		reportMedium = "2";  //Select Code for Paper medium
	}
	
	public String getReportingCity() {
		return reportingCity;
	}
	
	public void setReportingCity() {
		reportingCity = new String("BALTIMORE"); //Always Baltimore
	}
	
	public String getDateReceived() {
		return dateReceived; 
	}
	
	public void setDateReceived() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //Always today?
		Date today = new Date();
		dateReceived = dateFormat.format(today).toString();
	}
	
	public String getLabSource() {
		return labSource;
	}
	
	public void setLabSource() {
		labSource = new String("A05.03");  //Default: private lab; update in the event that hospital labs are to be processed
	}

}
