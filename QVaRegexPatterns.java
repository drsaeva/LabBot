/**
  * Regex Patterns specific to Quest VA labs, to be accessed in various automation classes (LabSource, QVaAuto)
  */

package labBot;

public class QVARegexPatterns {
	
	/*
	 * Pattern for identifying Quest VA Labs 
	 * - Quest CA and Quest VA both are under "Nichols Institute" 
	 * - Street names are unique for each
	 */
	protected static final String questVaIdentifier = "14225 NEWBROOK DRIVE";
	
	/*
	 * Pattern for line containing Date Completed [1]
	 * Also provided - ImmutableMap for converting 3-letter month names to 2-digit representation
	 */
	protected static final String dateCompleteLine = "Printed\\s(\\d{2}\\s[A-Z]{3}\\s\\d{4})\\sIncluding";
	
	/*
	 * Pattern for line containing:
	 * Ordering Facility of Test [1]
	 * "City, State" for Ordering Facility [2]
	 * Ciy of Ordering Facility [3]
	 */
	protected static final String orderingFacilityLine = "([A-Z ]+[A-Z]+),[A-Z ]+[A-Z]+,\\s\\d+[A-Z ]+[A-Z]+,"
			+ "\\s([A-Z ]+),\\s[A-Z]{2}\\s\\d{5}+";
	
	/*
	 * Pattern for line containing:
	 * Physician Name "Last, First" format [1, 2]
	 * Case Name "Last, First" format [3, 4]
	 * Case Sex [5]
	 * Test type [6] - ([7] is a shortened version of the test type indicating the organismal source, e.g. HIV or CD4)
	 * Test result [8]
	 * Test result in raw numeric format [9], in instance it exists (e.g. for RNA or CD4 quantification)
	 */
	protected static final String caseNameLine = ".+\\s+([A-Za-z]+),\\s+([A-Za-z]+)\\s+\\d{2}\\/\\d{2}\\/\\d{4}"
			+ "\\s+([A-Za-z]+),\\s+([A-Za-z]+).+([A-Z])\\s+((HIV|HIV 1|HIV-1|CD4)\\s+[A-Za-z/]+)[ ,]+(([0-9]+)"
			+ "\\.?\\s+[A-Za-z,/%.]+|[A-Za-z]+)";
	
	/*
	 * Pattern for line containing: 
	 * Case DOB [1]
	 * SSN - present or not [2]
	 * Sample collection date [3]
	 */
	protected static final String dobLine = "DOB\\s(\\d{2}\\/\\d{2}\\/\\d{4})\\s+Race\\s+SSN\\s+(\\d{9}|\\s+)\\s+"
			+ "Collected\\s(\\d{2}\\/\\d{2}\\/\\d{4})";
	
	/*
	 * Pattern for line containing case street address [0 or 1]
	 */
	protected static final String streetAddressLine = "\\s?(\\d+\\s[A-Z]+\\s[A-Z ]+)\\s?";
	
	/*
	 * Pattern for line containing:
	 * Case City for address [1]
	 * Case State for address [2]
	 * Case Zip Code for address [3]
	 */
	protected static final String cityStateZipLine = "([A-Z]+|[A-Z]+\\s[A-Z]+|[A-Z]+\\s[A-Z]+\\s[A-Z]+)\\s+,\\s[A-Z]{2}\\s(\\d{5})";
	

}
