package com.academy.techcenture.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private XSSFWorkbook workBook;
    private XSSFSheet workSheet;

    public ExcelReader(String path, String sheetName) {
        File file = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            workBook = new XSSFWorkbook(inputStream);
            workSheet = workBook.getSheet(sheetName);
            workBook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Object[][] getData() {

        int rows = workSheet.getLastRowNum();
        int cols = workSheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            Map<String, String> map = new HashMap<>();

            for (int j = 0; j < cols; j++) {
                XSSFCell cell = workSheet.getRow(i + 1).getCell(j);

                if (cell == null) {
                    System.out.println();
                }

                map.put(workSheet.getRow(0).getCell(j).toString(),
                        cell == null ? "" : cell.toString());
            }
            data[i][0] = map;
        }
        return data;
    }

}
