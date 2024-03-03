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
            String[][] selectors = {
                    {"section.section_topstory", "Top Story"},
                    {"div#automation_TV0", "Top Story"},
                    {"div#kinhdoanh", "Kinh Doanh"},
                    {"div#batdongsan", "Bất Động Sản"},
                    {"div#thethao", "Thể Thao"},
                    {"div.box-giaitri-v2", "Giải Trí"},
                    {"div#suckhoe", "Sức Khoẻ"},
                    {"div#doisong", "Đời Sống"},
                    {"div#giaoduc", "Giáo Dục"},
                    {"section.section-podcast-v3", "Podcast"},
                    {"section#khoahoc_sohoa", "Khoa Học & Số Hoá"},
                    {"section.section_video_home", "Video"},
                    {"hgroup.thoisu", "Thời Sự"},
                    {"section#block_hv_dulich", "Du Lịch"},
                    {"section#block_hv_xe", "Xe"},
                    {"div.box-tamsu-home", "Tâm Sự"},
                    {"div.box-tamsu-home", "Tâm Sự"},
                    {"div.automation_Photo", "Ảnh"},
                    {"div.box-xemnhieu", "Xem Nhiều"},
                    {"div.box-news-other-site", "Trang Khác"},
                    {"div.wrap-box-business", "Thông Tin Doanh Nghiệp"},
            };

            for (String[] selector : selectors) {
                List<WebElement> elements = driver.findElements(By.cssSelector(selector[0] + " a"));
                List<String> links = new ArrayList<>();
                for (WebElement element : elements) {
                    String href = element.getAttribute("href");
                    if (href != null && !href.isEmpty()) {
                        links.add(href);
                    }
                }
                linksMap.put(selector[1], links);
            }

            String[][] specialSelectors = {
                    {"hgroup.thoisu", "Thời Sự"},
                    {"hgroup.thegioi", "Thế Giới"},
                    {"hgroup.phapluat", "Pháp Luật"},
                    {"hgroup.ykien", "Ý Kiến"},
                    {"hgroup.cuoi", "Thư Giãn"},
                    {"hgroup.infographics", "Infographics"},
            };

            for (String[] specialSelector : specialSelectors) {
                List<WebElement> hgroupElements = driver.findElements(By.cssSelector(specialSelector[0]));
                List<String> links = new ArrayList<>();
                for (WebElement hgroupElement : hgroupElements) {
                    WebElement parentDiv = hgroupElement.findElement(By.xpath("./ancestor::div[contains(@class, 'box-category')]"));
                    List<WebElement> linkElements = parentDiv.findElements(By.tagName("a"));

                    for (WebElement linkElement : linkElements) {
                        String href = linkElement.getAttribute("href");
                        if (href != null && !href.isEmpty()) {
                            links.add(href);
                        }
                    }
                }

                linksMap.put(specialSelector[1], links);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return linksMap;
    }


}

