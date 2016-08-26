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
	
	private final Map<String,String> convertAlphaMonthToNum = ImmutableMap.<String,String>builder()
				.put("JAN","01").put("FEB","02").put("MAR","03").put("APR","04")
				.put("MAY","05").put("JUN","06").put("JUL","07").put("AUG","08")
				.put("SEP","09").put("OCT","10").put("NOV","11").put("DEC","12")
				.build();
	
	private String dateCompleteAdjusted;
	
	/*public static void main(String[] args) {
		new QuestVaScanner();
		
	}*/
	
	public QuestVaScanner() {
		// TODO Auto-generated constructor stub
		
				
		for (String identifyLabSource : ocrResults) {
			Pattern identifyQva = Pattern.compile(QVaRegexPatterns.questVaIdentifier);
			Matcher m = identifyQva.matcher(identifyLabSource);
			if (m.find()) {
				for (String line : ocrResults) {	
					if (doRegex(line, QVaRegexPatterns.dateCompleteLine)){
						adjustDateCompleteFormat();
						LabDocData.createLabDocData(dataInDoc, "dateComplete", dateCompleteAdjusted);
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
	
	private void adjustDateCompleteFormat() {
		String month = convertAlphaMonthToNum.get(matchResults.get(1));
		String day = matchResults.get(0);
		String year = matchResults.get(2);
		dateCompleteAdjusted = month+"/"+day+"/"+year;
	}
	
	protected void setLabName() {
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "labName", "49D0221801");
	}

}
