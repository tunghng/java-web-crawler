package com.example.webcrawler.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    public Map<String, List<String>> getLinksBySection(String url) {
        Map<String, List<String>> sectionLinks = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url).get();

            // Assuming each section is identifiable by the <section> tag
            Elements sections = doc.select("section");

            for (Element section : sections) {
                // Use the section's class or id as a key. Adjust based on actual HTML structure.
                String sectionKey = section.className(); // or use .id() for id attribute

                List<String> links = new ArrayList<>();
                Elements linkElements = section.select("a[href]");

                for (Element linkElement : linkElements) {
                    String link = linkElement.attr("abs:href");
                    links.add(link);
                }

                // Only add sections that contain links
                if (!links.isEmpty()) {
                    sectionLinks.put(sectionKey, links);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.asList(sectionLinks));
        return sectionLinks;

    }
}

