package com.example.webcrawler.controllers;

import com.example.webcrawler.services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/crawl")
    public ResponseEntity<List<String>> getLinks(@RequestParam String link) {
        return ResponseEntity.ok(crawlerService.getAllLinks(link));
    }
}
