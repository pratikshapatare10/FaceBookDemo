package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFile_Reader {

	public static String[][] getData() throws EncryptedDocumentException, IOException
	{
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/TestData/FacebookTestData.xlsx");
		
		Sheet SHEET = null;
		String[][] arrayExcelData = null;
		try {
			SHEET = WorkbookFactory.create(file).getSheet("Sheet1");
			
			int totalNoOfRows = SHEET.getLastRowNum();
			int totalNoOfCols = SHEET.getRow(0).getLastCellNum();
			System.out.println(totalNoOfRows+"   "+totalNoOfCols);
			
			arrayExcelData = new String[totalNoOfRows][totalNoOfCols];
			
			for (int i= 1 ; i <= totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = SHEET.getRow(i).getCell(j).getStringCellValue();

				}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrayExcelData;
		
	}
}
