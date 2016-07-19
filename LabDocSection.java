package labBot;

import java.util.ArrayList;

public abstract class LabDocSection {
	
	/*
	 * Unsure if this class still needs to be Abstract:
	 * 		- depends if regex methods should be global/simplified, or local
	 * 			* first Class extension: FormInfo
	 * 				- many Class variables are defaulted and do not require regex
	 * 				- one variable requiring regex draws from Identification Class extension
	 * 				- remaining variable requires a simple regex for a date (getDateComplete())
	 * 					* Possibility: global Date regex called/utilized for variable setters
	 * 		- are there other abstract methods that could/should be inherited to simplify design?
	 *  
	 */

	public ArrayList<String> ocrResults = new ArrayList<String>();
	
	public LabDocSection() {
		
		DoOCR labDoc = new DoOCR();
		this.ocrResults = labDoc.getOCRResults();

	}
	
}
