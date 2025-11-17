package com.shuiqingzheng.module.recommend.entity;

import lombok.Data;

@Data
public class Recommend {
    private Long id;
    private String type; // anime, movie, book
    private String title;
    private String author; // 作者/导演
    private String genre;
    private Double rating;
    private Integer year; // publishYear for books
    private String description;
    private String coverUrl;

    // bangumi相关字段
    private Long bangumiId; // bangumi平台的ID
    private String bangumiUrl; // bangumi平台的URL
}
