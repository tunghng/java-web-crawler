package com.example.webcrawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crawled_data")
public class CrawledData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String section;
    @ManyToOne
    @JoinColumn(name = "crawl_attempt_id")
    private CrawlAttempt crawlAttempt;
}
