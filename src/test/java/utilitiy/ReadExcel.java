package utilitiy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public Object [] [] readExcelData(String filePath,String sheetName) throws IOException{
		
	//	String filePath="./Data1.xlsx";
		
	//	String sheetName="Sheet1";
		
		File src= new File(filePath);
		FileInputStream fis= new FileInputStream(src);
		XSSFWorkbook wb1= new XSSFWorkbook(fis); 
		XSSFSheet sheet1 = wb1.getSheet(sheetName);
		int rowCount = sheet1.getLastRowNum();
		int columnCount = sheet1.getRow(0).getLastCellNum();
		 Object[][] data = new Object[rowCount][columnCount];
		 for (int i = 0; i < rowCount; i++) {
		        Row row = sheet1.getRow(i+1);
		        for (int j = 0; j < columnCount; j++) {
		        	//System.out.println(row.getCell(j).toString());
		         data[i][j] = row.getCell(j).toString();
		        }
		    }
		return data;
	}
}