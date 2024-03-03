package com.example.webcrawler.controller;

import com.example.webcrawler.service.CrawlerService;
import com.example.webcrawler.service.ExcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CrawlerController.class)
public class CrawlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrawlerService crawlerService;

    @MockBean
    private ExcelService excelService;

    @Test
    public void exportLinksTest() throws Exception {
        String url = "http://example.com";
        byte[] mockExcelFile = new byte[0]; // simulate an empty Excel file for simplicity

        // Mock the service layer
        given(excelService.createExcelFile(new HashMap<String, List<String>>())).willReturn(mockExcelFile);

        // Perform a request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/exportLinks")
                        .param("url", url))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ExportedLinks.xlsx"))
                .andExpect(content().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")))
                .andExpect(content().bytes(mockExcelFile));
    }
}
