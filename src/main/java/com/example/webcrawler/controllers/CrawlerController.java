package com.example.webcrawler.controllers;

import com.example.webcrawler.services.CrawlerService;
import com.example.webcrawler.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private ExcelService excelService;

    @GetMapping("/exportLinks")
    public String exportLinks(@RequestParam String url) {
        List<String> links = crawlerService.getAllLinks(url);
        String fileName = "ExportedLinks.xlsx"; // Consider generating a unique name or allowing the user to specify one
        return excelService.createExcelFile(links, fileName);
    }
}
