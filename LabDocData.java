/**
 * 
 * Generic data class for instantion any time a new variable is regexed from the source document.
 * Objects are instantiated via calling the createLabDocData() static method, which calls the class constructor
 * 	and maps the object to a string describing the data source (equivalent to the value of dataSourceType)
 * This map is to be referenced by automation classes (currently LabDocScanner, but others coming) to map
 * 	LabDocData objects to specific LabDocSections, and facilitate access for automated data entry into eHARS
 * 
 * 
 */

package labBot;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class LabDocData {

	private String dataSourceType, dataValue, dataFieldHtmlId, dataSection;
	private final static Map<String, String> dataSource = ImmutableMap.<String,String>builder()
			.put("reportingCity", "repHlthDeptName")
			.put("dateReceived", "receiveDt")
			.put("documentSource", "documentSourceCd")
			.put("isNewCase", "initinvest")
			.put("reportMedium", "rptMedium")
			.put("surveillanceMethod", "survMethod")
			.put("dateComplete", "completeDt")
			.put("lastName", "lastName")
			.put("firstName", "firstName")
			.put("middleName", "middleName")
			.put("lastName", "lastName")
			.put("zipCode", "zipCd")
			.put("streetAddress", "streetAddress1")
			.put("streetAddressExtra", "streetAddress2")
			.put("cityName", "cityName")
			.put("cntyName", "cntyName")
			.put("phoneNumber", "phone1")
			.put("idType", "idCd")
			.put("idValue", "idValue")
			.put("birthSex", "birthSex")
			.put("dob", "dob")
			.build();
	
	public static void createLabDocData(HashMap<String, LabDocData> dataInDoc, String dataSourceType, String dataValue) {
		dataInDoc.put(dataSourceType, new LabDocData(dataSourceType, dataValue));
	}
	
	public LabDocData(String dataSourceType, String dataValue) {
		// TODO Auto-generated constructor stub
		
		setDataValue(dataValue);
		setDataSection(dataSection);
		setDataSourceType(dataSourceType);
		setDataFieldHtmlId(dataSourceType);
	}
	
	protected void setDataSourceType(String dataSourceType) {
			this.dataSourceType = dataSourceType;
	}
	
	public String getDataSourceType() {
		return dataSourceType;
	}
	
	private void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	
	public String getDataValue() {
		return dataValue;
	}
	
	private void setDataFieldHtmlId(String dataSourceType) {
		this.dataFieldHtmlId = getDataFieldHtmlIdFromHash(dataSourceType);
	}
	
	public String getDataFieldHtmlId() {
		return dataFieldHtmlId;
	}
	
	private String getDataFieldHtmlIdFromHash(String dataSourceType) {
		return dataSource.get((dataSourceType));
	}
	
	private void setDataSection (String dataSection) {
		this.dataSection = dataSection;
	}
	
	public String getDataSection () {
		return dataSection;
	}
	
}
