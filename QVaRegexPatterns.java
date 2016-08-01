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
	public static final String questVAIdentifier = "14225 NEWBROOK DRIVE";
	
	/*
	 * Pattern for line containing:
	 * Ordering Facility of Test [1]
	 * "City, State" for Ordering Facility [2]
	 * Ciy of Ordering Facility [3]
	 */
	public static final String orderingFacilityLine = "([A-Z ]+),.+,\\s+(([A-Z ]+),\\s+[A-Z]{2})";
	
	/*
	 * Pattern for line containing:
	 * Physician Name "Last, First" format [1, 2]
	 * Case Name "Last, First" format [3, 4]
	 * Case Sex [5]
	 * Test type [6] - ([7] is a shortened version of the test type indicating the organismal source, e.g. HIV or CD4)
	 * Test result [7]
	 * Test result in raw numeric format [8], in instance it exists (e.g. for RNA or CD4 quantification)
	 */
	public static final String caseNameLine = "([A-Za-z]+),\\s+([A-Za-z]+).+([A-Za-z]+),\\s+([A-Za-z]+).+([A-Z])\\s+((HIV|HIV-1|CD4)\\s+[A-Za-z/]+)[\\s,]+(([0-9]+)\\.?\\s+[A-Za-z,/%.]+|[A-Za-z]+)";
	
	/*
	 * Pattern for line containing: 
	 * Case DOB [1]
	 * Sample collection date [2]
	 */
	public static final String dobLine = "DOB\\s+(\\d{2}\\/\\d{2}\\/\\d{4}).+Collected\\s+(\\d{2}\\/\\d{2}\\/\\d{4})";
	
	/*
	 * Pattern for line containing case street address [0 or 1]
	 */
	public static final String streetAddressLine = "(\\d?\\s+([A-Z]+\\s+[A-Z]+|.+))";
	
	/*
	 * Pattern for line containing:
	 * Case City for address [1]
	 * Case State for address [2]
	 * Case Zip Code for address [3]
	 */
	public static final String cityStateZipLine = "([A-Z ]+)\\s+,\\s+([A-Z]{2})\\s+(\\d{5})";
	

}
