package com.leedong.covid.dao;

import com.leedong.covid.model.News;

import java.util.List;

public interface NewsDao {

    void saveNews(List<News> newsList);

    boolean getNewsByUrl(News news);

}
