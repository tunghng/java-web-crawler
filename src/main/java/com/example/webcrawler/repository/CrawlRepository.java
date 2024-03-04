package com.example.webcrawler.repository;

import com.example.webcrawler.model.CrawlAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlRepository extends JpaRepository<CrawlAttempt, String> {

}
