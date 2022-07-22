package com.leedong.covid.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leedong.covid.model.News;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/news")
    public List<String> updateNews() throws JsonProcessingException {
        String url = "https://www.hpa.gov.tw/wf/newsapi.ashx?fbclid=IwAR11w4I_brMYrgl7iAummGlQV8hKxvdf3NWmUWlp0Cadyy2DHAnPaST6DxM";
       // String url = "http://localhost:8082/ch";
        RestTemplate restTemplate =new RestTemplate();
        RequestEntity request = RequestEntity.get(URI.create(url))
                                             .accept(MediaType.APPLICATION_JSON)
                                             .build();

        ResponseEntity<String>response = restTemplate.exchange(
                request,
                String.class
        );
        String result = response.getBody().toString();


        News news = new News();

        List<String> json = new ArrayList<>();


        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i<jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title = jsonObject.getString("標題");

            news.setTitle(title);
            json.add(objectMapper.writeValueAsString(news));

        }
        return json;





//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Map<String,String> map = objectMapper.readValue(result,Map.class);



    }
}
