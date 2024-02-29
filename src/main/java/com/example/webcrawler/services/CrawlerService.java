package com.example.webcrawler.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

}
