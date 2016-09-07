package labBot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class LabData implements LabDocSection {
	
	public static List<String> dataInLabData;
	static {
		ArrayList<String> tempLabData = new ArrayList<String>();
		//tempLabData.add("labName");
		tempLabData.add("facilityCity");
		tempLabData.add("facilityName");
		tempLabData.add("physicianLastName");
		tempLabData.add("physicianFirstName");
		tempLabData.add("dateCollected");
		tempLabData.add("testType");
		dataInLabData = Collections.unmodifiableList(tempLabData);
	}
	
	public final static Map<String, String> testTypeSelectValues = ImmutableMap.<String,String>builder()
			.put("HIV Ag/Ab", "EC-004")
			.put("HIV-1 Ab", "EC-005") 
			.put("HIV-2 Ab", "EC-005")
			.put("HIV-1 RNA", "EC-014")	//TODO Check with Carly to see if 'HIV-1 RNA' is used for qualitative RNA tests as well
			.put("CD4 COUNT", "EC-016")
			.put("CD4 PCT", "EC-016")
			.build();
	
}
