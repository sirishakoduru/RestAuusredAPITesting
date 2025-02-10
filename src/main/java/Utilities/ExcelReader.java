package Utilities;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static ExcelReader excelReader = null;
	public ExcelReader(String excelFilePath) {
		path = excelFilePath;
	}

	private FileInputStream fi;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private String path;

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	private static boolean isRowEmpty(Row row) {
		if (Objects.isNull(row)) {
			return true;
		}
	    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
	        Cell cell = row.getCell(j);
	        if (cell != null && cell.getCellType() != CellType.BLANK) {
	            return false;
	        }
	    }
	    return true;
	}




}



