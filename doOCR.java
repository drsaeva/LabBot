/**
 *
 * This class encapsulates OCR analysis functions provided by Tesseract. The doOCR(String path) method
 * 	is called in an automation class, currnetly with a hard-coded string. 
 * Eventually, this class will integrate with a file handler class that will locate and enable user-selection
 * 	via GUI of scanned images for processing.
 * 
 */

package labBot;

import java.io.File;
import java.util.ArrayList;
import net.sourceforge.tess4j.*;

public class doOCR {

	public void doOCR(String path) {
		ITesseract instance = new Tesseract(); 		// JNA Interface Mapping
		System.setProperty(					   		
				"java.library.path", "lib");
		instance.setDatapath("data");
		instance.setLanguage("eng");
		
		ArrayList<String> ocrResults = new ArrayList<String>();
		File labDoc = new File(path
				//"E:/Tess4J/test/resources/test-data/eurotext.bmp"
				);
		
		try {
			String line = instance.doOCR(labDoc);
			ocrResults.add(line);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println(ocrResults);
	}
	
	public ArrayList<String> getOCRResults() {
		return ocrResults;
	}
}
