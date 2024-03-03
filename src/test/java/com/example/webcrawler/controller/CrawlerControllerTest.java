package com.example.webcrawler.controller;

import com.example.webcrawler.service.CrawlerService;
import com.example.webcrawler.service.ExcelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CrawlerController.class)
class CrawlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrawlerService crawlerService;

    @MockBean
    private ExcelService excelService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void exportLinks() throws Exception {
        // Prepare mock services
        String testUrl = "http://example.com";
        HashMap<String, List<String>> linksMap = new HashMap<>();
        linksMap.put("Section1", Arrays.asList("http://example.com/link1", "http://example.com/link2"));
        byte[] mockExcelFile = new byte[]{1, 2, 3, 4}; // Dummy Excel file content

        given(crawlerService.getLinksBySection(testUrl)).willReturn(linksMap);
        given(excelService.createExcelFile(linksMap)).willReturn(mockExcelFile);

        // Perform request and expect a specific status, content type, and header
        mockMvc.perform(get("/exportLinks").param("url", testUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ExportedLinks.xlsx"))
                .andExpect(content().bytes(mockExcelFile));
    }
}
