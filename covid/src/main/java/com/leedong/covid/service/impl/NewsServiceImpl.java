package com.leedong.covid.service.impl;

import com.leedong.covid.dao.NewsDao;
import com.leedong.covid.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public void updateNews(String url) {
        RestTemplate restTemplate = new RestTemplate();


    }
}
