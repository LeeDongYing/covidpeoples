package com.leedong.covid.dao.impl;

import com.leedong.covid.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewsDaoImpl implements NewsDao {
    @Autowired(required = false)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


}
