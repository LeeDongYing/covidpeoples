package com.leedong.covid.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class News {

    private String title;

   // private String content;

//    private String connectionUrl;
//    @JsonProperty("附加檔案")
//    private Data data;
//    @JsonProperty("發布日期")
//    private Date createdDate;
//    @JsonProperty("修改日期")
//    private Date modifiedDate;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }


}
