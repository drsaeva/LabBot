package labBot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demographics {

	public static List<String> dataInDemographics;
	static {
		ArrayList<String> tempDemo = new ArrayList<String>();
		tempDemo.add("dob");
		tempDemo.add("birthSex");
		dataInDemographics = Collections.unmodifiableList(tempDemo);
	}

}
