package com.example.webcrawler.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    public byte[] createExcelFile(HashMap<String, List<String>> sectionLinksMap) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Links");

        // Creating header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Section");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("URL");

        int rowNum = 1;
        // Iterating through the HashMap
        for (Map.Entry<String, List<String>> entry : sectionLinksMap.entrySet()) {
            String section = entry.getKey();
            List<String> links = entry.getValue();

            // Creating a row for each link in the section
            for (String link : links) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(section);
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(link);
            }
        }

        // Auto-size the columns
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        // Writing the workbook to a byte array output stream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bos.toByteArray();
    }
}
