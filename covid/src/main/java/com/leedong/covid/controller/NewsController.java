package com.leedong.covid.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leedong.covid.model.News;
import com.leedong.covid.service.NewsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NewsController {

@Autowired(required = false)
private ObjectMapper objectMapper;

@Autowired
private NewsService newsService;


    @GetMapping("/news")
    public List<News> updateNews() throws JsonProcessingException {
        String url = "https://www.hpa.gov.tw/wf/newsapi.ashx?fbclid=IwAR11w4I_brMYrgl7iAummGlQV8hKxvdf3NWmUWlp0Cadyy2DHAnPaST6DxM";
       // String url = "http://localhost:8082/ch";

        RestTemplate restTemplate =new RestTemplate();

        //使用方法和header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity(headers);

        //搜尋條件
        url +="&keyword=戒菸";

        ResponseEntity<String>response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );
        List<News> newsList = newsService.transfer(response);

        return newsList;





//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Map<String,String> map = objectMapper.readValue(result,Map.class);



    }
}
