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

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Autowired(required = false)
    private ObjectMapper objectMapper;

    @Override
    public List<News> getNewsList(ResponseEntity<String> response) throws JsonProcessingException {
        return transfer(response);
    }

    @Override
    public void saveNews(ResponseEntity<String> response) throws JsonProcessingException {
        List<News> newsList =  transfer(response);
        List<News> nList = new ArrayList<>();
        for (int i = 0;i<newsList.size();i++){
            News news = newsList.get(i);
            if (newsDao.getNewsByUrl(news) != true){
                nList.add(newsList.get(i));
            }else{
                continue;
            }
        }

        newsDao.saveNews(nList);
    }



    private List<News> transfer(ResponseEntity<String> response)throws JsonProcessingException{
        String result = response.getBody().toString();

        List<News> newsList = new ArrayList<>();

//      把object轉乘String
//      String json = new String();

        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            News news = new News();

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            news.setTitle(jsonObject.getString("標題"));
            news.setContent(jsonObject.getString("內容"));

            String[] jArray = objectMapper.readValue(jsonObject.getJSONArray("附加檔案").toString(),String[].class);
            news.setDataList(jArray);

            news.setConnectionUrl(jsonObject.getString("連結網址"));
            news.setCreatedDate(jsonObject.getString("發布日期"));
            news.setModifiedDate(jsonObject.getString("修改日期"));

            newsList.add(news);

//            json += objectMapper.writeValueAsString(news);
        }
        return newsList;
    }
}
