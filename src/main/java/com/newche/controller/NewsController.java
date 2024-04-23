package com.newche.controller;

import com.newche.model.News;
import com.newche.model.Tag;
import com.newche.service.NewsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/news") // Base path for all news-related endpoints
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);
    private final NewsService newsService;

    
    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // Create a new news item
    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News createdNews = newsService.createNews(news);
        return ResponseEntity.ok(createdNews);
    }

    // Retrieve a news item by ID
    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable String id) {
        News news = newsService.getNewsById(id);
        return ResponseEntity.ok(news);
    }

    // Retrieve news by tags
    @GetMapping("/by-tags")
    public ResponseEntity<List<News>> getNewsByTags(@RequestParam List<String> tagIds) {
        List<News> newsList = newsService.getNewsByTags(tagIds);
        return ResponseEntity.ok(newsList);
    }    

    // Get all news items
    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }
    
    // Retrieve news by date@GetMapping("/by-date")
    @GetMapping("/by-date")
    public ResponseEntity<List<News>> getNewsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        log.info("Received date: {}", date);
        List<News> newsList = newsService.getNewsByDate(date);
        log.info("Query returned {} results", newsList.size());
        return ResponseEntity.ok(newsList);
    }

    public ResponseEntity<List<News>> getAllTags() {
        List<News> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

    // Delete a news item
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable String id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok().build();
    }

    // Additional news-related endpoints...

}
