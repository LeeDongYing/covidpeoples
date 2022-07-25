package com.leedong.covid.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leedong.covid.model.News;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NewsService {
    void createNews(ResponseEntity<String> response)throws JsonProcessingException;

    List<News> getNewsList(ResponseEntity<String> response)throws JsonProcessingException ;

}
