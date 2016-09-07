package labBot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocalFields implements LabDocSection {
	
	public static List<String> localFieldsClassNames;
	static {
		ArrayList<String> tempLocalFields = new ArrayList<String>();
		tempLocalFields.add("localFields[0].localFieldValue");
		tempLocalFields.add("localFields[1].localFieldValue");
		tempLocalFields.add("localFields[2].localFieldValue");
		tempLocalFields.add("localFields[3].localFieldValue");
		localFieldsClassNames = Collections.unmodifiableList(tempLocalFields);
	}
	
	private final Pattern parseName = Pattern.compile("User:\\s([A-Za-z]+\\s[A-Za-z]+\\s[A-Za-z]|[A-Za-z]+\\s[A-Za-z])");
	private final Pattern parseNameShort = Pattern.compile("(\\w)\\w+\\s(\\w)\\w+\\s(\\w)\\w|(\\w)\\w+\\s(\\w)\\w+");

	
	//creates LabDocData from string after running parseName twice, value is First/Last initials concatenated as String from parseName
	protected void parseUser(WebDriver driver) {
		for (WebElement element : driver.findElements(By.xpath("//strong"))) {
			if (element.getText().contains("User:")) {
				String userName = parseName(element.getText(), parseName);
				String userNameShort = parseName(userName.toUpperCase(), parseNameShort);
				LabDocData.createLabDocData(LabDocScanner.dataInDoc, "localFieldsUser", userNameShort);
			}
			
		}
	}
	
	//performs regex on input string, concatenates match results into single returned string
	protected String parseName(String input, Pattern p) {
		Matcher m = p.matcher(input);
		String parsedName = "";
		while (m.find()) {
			ArrayList<String> results = new ArrayList<String>();
			for (int i=0; i<m.groupCount(); i++) {
				results.add(m.group(i));
			}
			for (String s : results) {
				parsedName.concat(s);
			}
		}
		return parsedName;
	}
	
}
