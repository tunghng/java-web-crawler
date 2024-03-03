package com.example.webcrawler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CrawlerServiceTest {

    @InjectMocks
    private CrawlerService crawlerService;

    @Mock
    private WebDriver mockDriver;

    @Mock
    private WebElement mockElement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        crawlerService.setDriver(mockDriver);
    }

    @Test
    void testGetLinksBySection() {
        // Mock behavior for WebDriver and WebElement
        when(mockElement.getAttribute("href")).thenReturn("http://test.example/link1", "http://test.example/link2");
        when(mockDriver.findElements(By.cssSelector("a"))).thenReturn(Arrays.asList(mockElement, mockElement));

        // Invoke the method under test
        HashMap<String, List<String>> result = crawlerService.getLinksBySection("http://test.example");

        // Assertions
        assertEquals(24, result.size(), "The size of the returned map should match the expected size.");

        verify(mockDriver, times(1)).get("http://test.example");
        verify(mockDriver, atLeastOnce()).findElements(By.cssSelector("section.section_topstory a"));
    }
}
