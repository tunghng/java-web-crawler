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
            // Handle specific sections and divs as per the structure.
            Elements sections = doc.select("section.section_topstory, section.section_stream_home, section.section-podcast-v3, section.section_video_home, section.section_container");
            for (Element section : sections) {
                String sectionKey = section.className().isEmpty() ? "Unnamed Section" : section.className();
                // For each section, find divs by id for specific categories
                Elements divs = section.select("div#kinhdoanh, div#batdongsan, div#thethao, div.box-giaitri-v2, div#suckhoe, div#doisong, div#giaoduc, section#khoahoc_sohoa, div#automation_Video, section#block_hv_dulich, section#block_hv_xe");
                for (Element div : divs) {
                    String divKey = div.id(); // Use id as the key
                    List<String> links = new ArrayList<>();
                    Elements linkElements = div.select("a[href]");
                    for (Element linkElement : linkElements) {
                        String link = linkElement.attr("abs:href");
                        links.add(link);
                    }
                    if (!links.isEmpty()) {
                        // Include the div id in the section key to differentiate between different divs within the same section
                        sectionLinks.put(sectionKey + " -> " + divKey, links);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sectionLinks);
        return sectionLinks;
    }


}

