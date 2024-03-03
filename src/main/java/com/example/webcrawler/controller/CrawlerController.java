package com.example.webcrawler.controller;

import com.example.webcrawler.service.CrawlerService;
import com.example.webcrawler.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private ExcelService excelService;

    @GetMapping("/exportLinks")
    public ResponseEntity<byte[]> exportLinks(@RequestParam String url) {
        HashMap<String, List<String>> links = crawlerService.getLinksBySection(url);
        byte[] excelFile = excelService.createExcelFile(links);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ExportedLinks.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }
}
