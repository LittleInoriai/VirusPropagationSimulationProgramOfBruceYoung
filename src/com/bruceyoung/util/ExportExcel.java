package com.bruceyoung.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
public class ExportExcel {
    public void WriteExcel(String excelPath, int[] rowData) throws Exception {
        FileInputStream fileInputStream=new FileInputStream(excelPath);  //��ȡexcel
        XSSFWorkbook Workbook=new XSSFWorkbook(fileInputStream);//�õ��ĵ�����
        XSSFSheet sheet=Workbook.getSheet("Sheet4");  //����name��ȡsheet��

        XSSFRow row=sheet.getRow(0);  //��ȡ��һ��

        FileOutputStream out=new FileOutputStream(excelPath);  //��excel��д����
        //int lastRowNum = sheet.getLastRowNum()+1;
        row=sheet.createRow(sheet.getLastRowNum()+1);
        row.createCell(0).setCellValue(rowData[0]); //���õ�һ������0��ʼ����Ԫ�������
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
