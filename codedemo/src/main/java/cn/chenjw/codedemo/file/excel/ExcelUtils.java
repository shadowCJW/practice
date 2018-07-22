package cn.chenjw.codedemo.file.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.measure.quantity.Velocity;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *  HSSFWorkBook：操作2003版本以前的（包括2003版本），扩展名.xls，该类在org.apache.poi:poi中

	XSSFWorkBook：操作2007版本以后的（包括2007版本），拓展名.xlsx，该类在org.apache.poi:poi-ooxml中

	SXSSFWorkBook：对于海量的数据进行操作
 * @author Administrator
 *
 */
public class ExcelUtils {
	private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx"; 
    
    public static void readExcel(String fileName){
    	 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
    	//1.读取Excel的对象  
        try {
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(new File(fileName)));
			if(fileName.endsWith(EXCEL_XLS)){
				//2.Excel工作薄对象  
	            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);  
	          //3.Excel工作表对象  
	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);  
	            //总行数  
	            int rowLength = hssfSheet.getLastRowNum()+1;  
	            //4.得到Excel工作表的行  
	            HSSFRow hssfRow = hssfSheet.getRow(0);  
	            //总列数  
	            int colLength = hssfRow.getLastCellNum();  
	            
	            for(int i = 0;i<rowLength;i++ ){
		            HSSFRow hssfRowI = hssfSheet.getRow(i); 
		            String rowValue = "";
		            for(int j =0;j<colLength;j++){
		            	HSSFCell cell = hssfRowI.getCell(j);
		            	 int cellType = cell.getCellType();  
		            	 String cellValue = "";
		                 switch (cellType) {  
	                        case Cell.CELL_TYPE_STRING:     // 文本  
	                            cellValue = cell.getRichStringCellValue().getString() + "#";  
	                            break;  
	                        case Cell.CELL_TYPE_NUMERIC:    // 数字、日期  
	                            if (DateUtil.isCellDateFormatted(cell)) {  
	                                cellValue = fmt.format(cell.getDateCellValue()) + "#";  
	                            } else {  
	                                cell.setCellType(Cell.CELL_TYPE_STRING);  
	                                cellValue = String.valueOf(cell.getRichStringCellValue().getString()) + "#";  
	                            }  
	                            break;  
	                        case Cell.CELL_TYPE_BOOLEAN:    // 布尔型  
	                            cellValue = String.valueOf(cell.getBooleanCellValue()) + "#";  
	                            break;  
	                        case Cell.CELL_TYPE_BLANK: // 空白  
	                            cellValue = cell.getStringCellValue() + "#";  
	                            break;  
	                        case Cell.CELL_TYPE_ERROR: // 错误  
	                            cellValue = "错误#";  
	                            break;  
	                        case Cell.CELL_TYPE_FORMULA:    // 公式  
	                            // 得到对应单元格的公式  
	                            //cellValue = cell.getCellFormula() + "#";  
	                            // 得到对应单元格的字符串  
	                            cell.setCellType(Cell.CELL_TYPE_STRING);  
	                            cellValue = String.valueOf(cell.getRichStringCellValue().getString()) + "#";  
	                            break;  
	                        default:  
	                            cellValue = "#";  
	                    }
		                 rowValue+=cellValue;
		            }
		            System.out.println(rowValue);
		            
	            }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
    }
    public static void writeExcel(){
    	 Workbook workbook = new XSSFWorkbook();  
         Sheet sheet = workbook.createSheet("0");  
         
         Row row = sheet.createRow(0);  
         CellStyle cellStyle = workbook.createCellStyle();  
         // 设置这些样式  
         cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        // cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
         cellStyle.setBorderBottom(CellStyle.BORDER_THIN);  
         cellStyle.setBorderLeft(CellStyle.BORDER_THIN);  
         cellStyle.setBorderRight(CellStyle.BORDER_THIN);  
         cellStyle.setBorderTop(CellStyle.BORDER_THIN);  
         cellStyle.setAlignment(CellStyle.ALIGN_CENTER);  
   
         row.createCell(0).setCellStyle(cellStyle);  
         row.createCell(0).setCellValue("姓名");  
   
         row.createCell(1).setCellStyle(cellStyle);  
         row.createCell(1).setCellValue("年龄");  
         workbook.setSheetName(0, "信息");  
         try {  
             File file = new File("E:/A/excel/0527.xlsx");  
             FileOutputStream fileoutputStream = new FileOutputStream(file);  
             workbook.write(fileoutputStream);  
             fileoutputStream.close();  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
   
    }
    public static void main(String[] args) {
		readExcel("E:/A/excel/1621.xls");
		writeExcel();
	}
}
