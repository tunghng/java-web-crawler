package com.example.webcrawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CrawlerService {
    public List<String> getAllLinks(String url) {
        List<String> links = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements linkElements = doc.select("a[href]");

            linkElements.forEach(element -> {
                String link = element.attr("abs:href");
                links.add(link);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return links;
    }


    public static HashMap<String, List<String>> getLinksBySection(String url) {
        System.setProperty("webdriver.chrome.driver", "/Users/htungg/Documents/java-web-crawler/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Runs Chrome in headless mode.
        WebDriver driver = new ChromeDriver(options);

        HashMap<String, List<String>> linksMap = new HashMap<>();

        try {
            driver.get(url);

            // Define your selectors here
            String[] selectors = {
                    "section.section_topstory",
                    "div#kinhdoanh",
                    "div#batdongsan",
                    "div#thethao",
                    "div.box-giaitri-v2",
                    "div#suckhoe",
                    "div#doisong",
                    "div#giaoduc",
                    "section.section-podcast-v3",
                    "section.section_container",
                    "section#_khoahoc_sohoa",
                    "section.section_video_home",
                    "div#automation_Video",
                    "section#block_hv_dulich",
                    "section#block_hv_xe"
            };

            for (String selector : selectors) {
                List<WebElement> elements = driver.findElements(By.cssSelector(selector + " a"));
                List<String> links = new ArrayList<>();
                for (WebElement element : elements) {
                    String href = element.getAttribute("href");
                    if (href != null && !href.isEmpty()) {
                        links.add(href);
                    }
                }
                linksMap.put(selector, links);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        System.out.println(Arrays.asList(linksMap));
        return linksMap;
    }


}

