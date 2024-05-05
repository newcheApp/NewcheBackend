package com.newche.service;

import com.newche.dao.NewsDAO;
import com.newche.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsService {

    private final NewsDAO newsDAO;
    // test 2
    @Autowired
    public NewsService(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    // Create a news item
    public News createNews(News news) {
        // Additional logic before saving the news (if necessary)
        return newsDAO.createNews(news);
    }

    // Retrieve a news item by ID
    public News getNewsById(String id) {
        return newsDAO.findNewsById(id);
    }

    public List<News> getNewsReverseOrder() {
        return newsDAO.findAllReverseOrder();
    }

    // Retrieve news by tags
    public List<News> getNewsByTags(List<String> tagIds) {
        return newsDAO.findNewsByTags(tagIds);
    }

    // Retrieve news by date
    public List<News> getNewsByDate(Date date) {
        return newsDAO.findNewsByDate(date);
    }

    // Delete a news item
    public void deleteNews(String id) {
        // Additional logic before deleting the news (if necessary)
        newsDAO.deleteNews(id);
    }

    public List<News> getAllNews() {
        return newsDAO.findAllNews();
    }

    public List<News> getNewsByTagsAndDate(List<String> tagIds, Date date) {
        return newsDAO.findNewsByTagsAndDate(tagIds, date);
    }

    // Additional methods as required...
}
