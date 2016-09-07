package labBot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Identification implements LabDocSection {
	
	public static List<String> dataInIdentification;
	static {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("firstName");
		temp.add("middleName");
		temp.add("lastName");
		temp.add("nameSuffix");
		temp.add("streetAddress");
		temp.add("streetAddressExtra");
		temp.add("zipCode");
		temp.add("cityName");
		temp.add("phoneNumber");
		temp.add("id");

		dataInIdentification = Collections.unmodifiableList(temp);
	}
	
	public Identification() {
		makeIdSelectObjects();
		System.out.println(LabDocScanner.dataInDoc.get("ssnSelect").getDataValue());
	}
	
	protected void makeIdSelectObjects() {
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "ssnSelect", "012");
		LabDocData.createLabDocData(LabDocScanner.dataInDoc, "mrnSelect", "049");
	}
	
}
