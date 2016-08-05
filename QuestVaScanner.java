/**
 * This extension of LabDocScanner performs analysis of a lab doc received from 
 * 	Quest VA, parsing the OCR'd document line by line for relevant data and creating
 * 	LabDocData objects ad-hoc within the consistent framework of the format followed
 * 	by docs from this source.
 */

package labBot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableList;

public class QuestVaScanner extends LabDocScanner {
	
	private final ImmutableList<String> dateCompleteLineData = ImmutableList.of(
			"dateComplete");
	private final ImmutableList<String> caseNameLineData = ImmutableList.of(
			"physicianLastName", "physicianFirstName", "lastName", "firstName",
			"birthSex");
	private final ImmutableList<String> orderingFacilityLineData = ImmutableList.of(
			"facilityName", "facilityCity");
	private final ImmutableList<String> dobLineData = ImmutableList.of(
			"dob", "SSN", "dateCollected");
	private final ImmutableList<String> streetAddressLineData = ImmutableList.of(
			"streetAddress");
	private final ImmutableList<String> cityStateZipLineData = ImmutableList.of(
			"cityName", "zipCode");
	
	public static void main(String[] args) {
		new QuestVaScanner();
		
	}
	
	/**
	 * Scanner Class Specific to Quest VA
	 */
	
	public QuestVaScanner() {
		// TODO Auto-generated constructor stub
		
				
		for (String identifyLabSource : ocrResults) {
			Pattern identifyQva = Pattern.compile(QVaRegexPatterns.questVaIdentifier);
			Matcher m = identifyQva.matcher(identifyLabSource);
			if (m.find()) {
				for (String line : ocrResults) {	
					if (doRegex(line, QVaRegexPatterns.dateCompleteLine)){
						makeLabDocDataFromMatches(dateCompleteLineData);
						//System.out.println(dataInDoc.get("dateComplete").getDataValue());
					} else if (doRegex(line, QVaRegexPatterns.orderingFacilityLine)) {
						makeLabDocDataFromMatches(orderingFacilityLineData);
						//System.out.println(matchResults);
						//System.out.println(dataInDoc.get("facilityCity").getDataValue());
					} else if (doRegex(line, QVaRegexPatterns.caseNameLine)) {
						makeLabDocDataFromMatches(caseNameLineData);
						//System.out.println(matchResults);
						//System.out.println(dataInDoc.get("birthSex").getDataValue());
					} else if (doRegex(line, QVaRegexPatterns.dobLine)) {
						makeLabDocDataFromMatches(dobLineData);
						//System.out.println(dataInDoc.get("dateCollected").getDataValue());
					} else if (doRegex(line, QVaRegexPatterns.streetAddressLine) /*&& dataInDoc.get("birthSex") != null*/) {
						makeLabDocDataFromMatches(streetAddressLineData);
						//LabDocData.createLabDocData(dataInDoc, "streetAddress", matchResults.get(0));
						//System.out.println(dataInDoc.get("streetAddress"));
						//System.out.println(dataInDoc.get("streetAddress").getDataValue());
					} else if (doRegex(line, QVaRegexPatterns.cityStateZipLine)) {
						makeLabDocDataFromMatches(cityStateZipLineData);
						//System.out.println(dataInDoc.get("zipCode").getDataValue());
					}
					
				}
				//System.out.println(dataInDoc.get("streetAddress"));
			}
		}
	}
	
	private void makeLabDocDataFromMatches(ImmutableList<String> dataInLine) {
		for (int j=0; j<dataInLine.size()&&j<5;j++) {
			LabDocData.createLabDocData(dataInDoc, dataInLine.get(j), matchResults.get(j));
		}
	}
	

}
