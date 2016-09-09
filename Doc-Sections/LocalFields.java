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
		try {
			WebElement userNameRaw = driver.findElement(By.xpath("//tr[1]/td/table/tbody/tr/td[1]/strong"));
			String a = userNameRaw.getAttribute("innerHTML");
			String userName = parseName(a, parseName);
			String userNameShort = parseName(userName.toUpperCase(), parseNameShort);
			LabDocData.createLabDocData(LabDocScanner.dataInDoc, "localFieldsUser", userNameShort);
		} catch (Exception e) {
			System.out.println("Username parsing failed, manually select user during QA");
		}
	}
	
	//performs regex on input name string containing 'User: &nbsp;' header and saves name
	protected String parseName(String input, Pattern p) {
		Matcher m = p.matcher(input);
		String parsedName = "";
		
		//while matching, save parsedName
		while (m.find()) {
			parsedName = m.group(1);
		}
		return parsedName;
	}
	
	//performs regex on input string, concatenates match results into single returned string
	protected String parseShortName(String input, Pattern p) {
		Matcher m = p.matcher(input);
		String parsedName = "";
		
		//while matching, concat matches to parsedName
		while (m.find()) {
			for (int i=1; i<=m.groupCount(); i++) {
				if (m.group(i).length() == 1) {
					parsedShortName = parsedShortName.concat(m.group(i));
				}
			}
		}
		return parsedName;
	}
	
}
