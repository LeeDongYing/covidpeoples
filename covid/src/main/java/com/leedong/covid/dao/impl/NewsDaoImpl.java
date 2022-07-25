package com.leedong.covid.dao.impl;

import com.leedong.covid.dao.NewsDao;
import com.leedong.covid.model.Data;
import com.leedong.covid.model.News;
import com.leedong.covid.rowmapper.NewsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NewsDaoImpl implements NewsDao {
    @Autowired(required = false)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void createNews(List<News> newsList) {
        String sql = "INSERT INTO news (title, content, connectUrl, created_date, modified_date)\n" +
                "VALUES (:title , :content ,:connectUrl  ,:createdDate ,:modifiedDate)";

        String sqlDataList = "INSERT INTO data (explanation, name, connection, connectionUrl) \n" +
                "VALUES (:explanation ,:name , :connection ,:connectionUrl)";

        MapSqlParameterSource[] mapSqlParameterSources = new MapSqlParameterSource[newsList.size()];

         for (int i = 0 ;i < newsList.size() ; i++){
             News news = newsList.get(i);

             for (int j = 0; j<news.getDataList().size();j++) {
                 Map<String, Object> map = new HashMap<>();
                 Data data = news.getDataList().get(j);
                 map.put("explanation", data.getExplanation());
                 map.put("name", data.getName());
                 map.put("connection", data.getConnection());
                 map.put("connectionUrl", news.getConnectionUrl());

                 namedParameterJdbcTemplate.update(sqlDataList,map);
             }

             mapSqlParameterSources[i] = new MapSqlParameterSource();
             mapSqlParameterSources[i].addValue("title",news.getTitle());
             mapSqlParameterSources[i].addValue("content",news.getContent());
             mapSqlParameterSources[i].addValue("connectUrl",news.getConnectionUrl());
             mapSqlParameterSources[i].addValue("createdDate",news.getCreatedDate());
             mapSqlParameterSources[i].addValue("modifiedDate",news.getModifiedDate());

         }
         namedParameterJdbcTemplate.batchUpdate(sql,mapSqlParameterSources);
    }

    @Override
    public boolean getNewsByUrl(News news) {
        String sql = "SELECT connectUrl FROM news WHERE connectUrl = :connectionUrl";

        Map<String,Object> map = new HashMap<>();
        map.put("connectionUrl",news.getConnectionUrl());

        List<News> newsList = namedParameterJdbcTemplate.query(sql,map,new NewsRowMapper());
        if (newsList.size()>0){
            return true;
        }else {
            return false;
        }
        }

    }

