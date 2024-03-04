package com.example.webcrawler.service;

import java.util.HashMap;
import java.util.List;

public interface CrawlerService {
    HashMap<String, List<String>> getLinksBySection(String url);
}
