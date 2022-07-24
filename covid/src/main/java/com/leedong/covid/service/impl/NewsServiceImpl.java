package com.leedong.covid.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leedong.covid.dao.NewsDao;
import com.leedong.covid.model.News;
import com.leedong.covid.service.NewsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Autowired(required = false)
    private ObjectMapper objectMapper;

    @Override
    public List<News> transfer(ResponseEntity<String> response) throws JsonProcessingException {
        String result = response.getBody().toString();

        List<News> newsList = new ArrayList<>();
//      把object轉乘String
//      String json = new String();

        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            News news = new News();
            
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title = jsonObject.getString("標題");
            news.setTitle(title);
            newsList.add(news);
     //       json += objectMapper.writeValueAsString(news);
        }

        return newsList;
    }
}
