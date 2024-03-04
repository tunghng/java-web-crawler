package com.example.webcrawler.repository;

import com.example.webcrawler.model.CrawledData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawledDataRepository extends JpaRepository<CrawledData, String> {
}
