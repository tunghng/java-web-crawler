package com.example.webcrawler.service;

import java.util.HashMap;
import java.util.List;

public interface ExcelService {
    byte[] createExcelFile(HashMap<String, List<String>> sectionLinksMap);
}
