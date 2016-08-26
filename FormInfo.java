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
		setUnchangingVariables();
		setDateReceived();
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
}
