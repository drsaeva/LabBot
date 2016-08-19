package labBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FormInfo {
	
	public static List<String> dataInFormInfo;
	static {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("reportingCity");
		temp.add("dateReceived");
		temp.add("documentSource");
		temp.add("isNewCase");
		temp.add("reportMedium");
		temp.add("surveillanceMethod");
		temp.add("dateComplete");
		dataInFormInfo = Collections.unmodifiableList(temp);
	}
	
	protected String dateReceived, isNewCase, dateComplete;
	protected final String reportingCity = "BALTIMORE"; 	//Always Baltimore
	protected final String documentSource = "A05.03";  		//Default: private lab; make setter in the event that other labs are to be processed
	protected final String reportMedium = "2";  			//Select Code for Paper-mailed medium
	protected final String surveillanceMethod = "P"; 		//Default: passive (P); make setter in the event that there should be another option
	
	protected final String sectionHtmlKeyword = "formInfoForm";
	
	/*protected static void mapFormInfoSection(HashMap<String, LabDocSection> sectionsInLabDoc) {
		sectionsInLabDoc.put("FormInfo", new FormInfo());
	}*/
	
	public FormInfo(/*String dateComplete*/) {
		// TODO Examine code - do I need anything else aside from these methods being run?
		
		setUnchangingVariables();
		setDateReceived();
		setNewCase();
		//setDateComplete(dateComplete);
		
		
	}
	
	private void setNewCase(/*newCaseBool*/) {
		// TODO code to set new case Y/N answer
		/*
		 * Requirements - regex for case name   <--- set via Identification Parsing Object
		 * 				- search for case name  <--- results likely set via SearchCase Object
		 * 						* needs - multiple methods of searching (DOB + fullname, DOB + L or F name, SSN, etc)
		 * 								- search logic: if DOB+FN fails, do alternate search; if all fail, new case (?)
		 * 				- must produce String from bool value: "Y", "N", or "U" 
		 */
		String isNewCase;
		/* 
		 * 
		 * if (newCaseBool) {
		 * 		isNewCase = "T";
		 * } else {
		 * 		isNewCase = "N";
		 * }
		 */
		isNewCase = "N"; //default behavior until search is coded
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "isNewCase", isNewCase);
		//this.isNewCase = isNewCase;
	}
	
	private void setDateReceived() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //Always today?
		Date today = new Date();
		String dateReceived = dateFormat.format(today).toString();
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "dateReceived", dateReceived);
	}
	
	private void setUnchangingVariables() {
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "reportingCity", reportingCity);
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "documentSource", documentSource);
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "reportMedium", reportMedium);
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "surveillanceMethod", surveillanceMethod);
	}
	
	/*
	private void setDateComplete(String dateComplete) {
		this.dateComplete = dateComplete;
	}
	
	public String getNewCase() {
		return isNewCase;
	}
	
	protected String getDateReceived(LabDocScanner scanner) {
		return scanner.getDataFromHashMap("dateReceived").getDataValue(); 
	}
	
	protected String getDateComplete() {
		return dateComplete;
	}
	
	public String getDocumentSource() {
		return documentSource;
	}
	
	public String getSurveillanceMethod() {
		return surveillanceMethod;
	}
	
	public String getReportMedium() {
		return reportMedium;
	}
	
	public String getReportingCity() {
		return reportingCity;
	}*/
	
	
	
}
