package com.java.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author wangning
 * @create 2020-12-15 8:59
 */
public class ReadExcelFile {
	public static void main(String[] args) {
		try {
			testXlsx();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void testXlsx() throws Exception {
		String filePath = "C:\\Users\\acer\\Desktop\\excel\\test.xlsx";
		File file = new File(filePath);
		if(file.exists()) {
			FileInputStream fileInputStream = new FileInputStream(file);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet1 = xssfWorkbook.getSheet("Sheet1");

			for (Row row : sheet1) {
				for (Cell cell : row) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
			XSSFRow row = sheet1.getRow(0);
			for (Cell cell:row) {
				System.out.println(cell+"\t");
			}
			XSSFRow row1 = sheet1.getRow(1);
			//获取 row 为1，cell为1的值
			String value = row.getCell(0).getStringCellValue();
			if (value.equals("姓名")) {
				System.out.println(value);
			}
		} else {
			System.out.println("文件不存在!");
		}
	}
	public static void testXls() {
		String filePath = "C:\\Users\\acer\\Desktop\\excel\\testxls.xls";
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(filePath);
			//读取Excel
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			//读取指定名称的Sheet
			HSSFSheet sheet1 = workbook.getSheet("Sheet1");
			for (Row row : sheet1) {
				for (Cell cell : row) {
					System.out.print(cell + "\t");
//					System.out.println();
				}
				System.out.println();
			}
			System.out.println(sheet1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
