package com.example.webcrawler.service;

import com.example.webcrawler.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcelServiceTest {

    @Test
    void createExcelFileTest() throws IOException {
        ExcelService excelService = new ExcelService();
        HashMap<String, List<String>> sectionLinksMap = new HashMap<>();
        sectionLinksMap.put("Test Section", List.of("http://example.com"));

        byte[] excelContent = excelService.createExcelFile(sectionLinksMap);

        // Use Apache POI to read the Excel file from the byte array
        try (InputStream is = new ByteArrayInputStream(excelContent)) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1); // Assuming row 0 is headers

            assertEquals("Test Section", row.getCell(0).getStringCellValue());
            assertEquals("http://example.com", row.getCell(1).getStringCellValue());
        }
    }
}
