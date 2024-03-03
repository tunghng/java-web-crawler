//package com.example.webcrawler.service;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//import com.example.webcrawler.service.CrawlerService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.openqa.selenium.WebDriver;
//
//import java.util.HashMap;
//import java.util.List;
//
//@ExtendWith(MockitoExtension.class)
//class CrawlerServiceTest {
//
//    @InjectMocks
//    private CrawlerService crawlerService;
//
//    // Example of mocking a WebDriver if you're going to abstract and inject it somehow
//    @Mock
//    private WebDriver mockWebDriver;
//
//    @Test
//    void testGetAllLinks() {
//        // Assume you have a way to mock Jsoup static calls or you refactor to make it testable
//        // This is a placeholder to indicate where your test logic would go
//    }
//
//    @Test
//    void testGetLinksBySection() {
//        // Mock interactions with WebDriver
//        when(mockWebDriver.findElements(...)).thenReturn(...);
//
//        // Execute the method to be tested
//        HashMap<String, List<String>> result = crawlerService.getLinksBySection("someUrl");
//
//        // Assertions and verifications
//        assertNotNull(result);
//        verify(mockWebDriver).findElements(...);
//    }
//}
