package com.leedong.covid.rowmapper;

import com.leedong.covid.model.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper implements RowMapper<News> {
    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();

        news.setConnectionUrl(rs.getString("connectUrl"));

        return news;
    }
}
