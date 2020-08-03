package com.bruceyoung.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
public class ExportExcel {
    public void WriteExcel(String excelPath, int[] rowData) throws Exception {
        FileInputStream fileInputStream=new FileInputStream(excelPath);  //获取excel
        XSSFWorkbook Workbook=new XSSFWorkbook(fileInputStream);//得到文档对象
        XSSFSheet sheet=Workbook.getSheet("Sheet4");  //根据name获取sheet表

        XSSFRow row=sheet.getRow(0);  //获取第一行

        FileOutputStream out=new FileOutputStream(excelPath);  //向excel中写数据
        //int lastRowNum = sheet.getLastRowNum()+1;
        row=sheet.createRow(sheet.getLastRowNum()+1);
        row.createCell(0).setCellValue(rowData[0]); //设置第一个（从0开始）单元格的数据
        row.createCell(1).setCellValue(rowData[1]);
        row.createCell(2).setCellValue(rowData[2]);
        row.createCell(3).setCellValue(rowData[3]);
        row.createCell(4).setCellValue(rowData[4]);

        out.flush();
        Workbook.write(out);
        out.close();
    }

//    public static void main(String[] args) throws Exception {
//        int[] rowData = new int[5];
//        rowData[0] = 1; rowData[1] = 2; rowData[2] = 3; rowData[3] = 4; rowData[4] = 5;
//        ExportExcel exportExcel = new ExportExcel();
//        exportExcel.WriteExcel("D://test.xlsx", rowData);
//    }
}
