package com.example.webcrawler.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CrawlerServiceTest {

    private WebDriver driver;
    private CrawlerService crawlerService;

    @BeforeEach
    public void setUp() {
        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "./chromedriver");

        // Configure ChromeDriver to run in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        // Initialize the CrawlerService with the actual driver
        crawlerService = new CrawlerService();
        crawlerService.setDriver(driver);
    }

    @Test
    public void getLinksBySectionIntegrationTest() {
        // Use a real, preferably stable and legal to crawl, URL for testing
        String testUrl = "https://vnexpress.net/";
        HashMap<String, List<String>> linksMap = crawlerService.getLinksBySection(testUrl);

        // Assert that the linksMap is not empty or contains expected values
        // The specific assertions will depend on the structure of the website and your requirements
        assertFalse(linksMap.isEmpty(), "Links map should not be empty");
        assertEquals(24, linksMap.size(), "Links map should contain 24 sections");
        for (Map.Entry<String, List<String>> entry : linksMap.entrySet()) {
            assertFalse(entry.getValue().isEmpty(), String.format("%s section should not be empty", entry.getKey()));
        }

    }

    @AfterEach
    public void tearDown() {
        // Quit the driver to close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
