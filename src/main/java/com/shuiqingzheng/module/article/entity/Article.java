package com.shuiqingzheng.module.article.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Article {
    private Long id;
    private String title;
    private String summary;
    private String content;
    private String category;
    private List<String> tags;
    private LocalDateTime publishDate;
    private LocalDateTime updateDate;
    private Integer viewCount;
    private String status; // published, draft
}
