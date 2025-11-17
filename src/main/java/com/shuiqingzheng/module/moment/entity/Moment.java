package com.shuiqingzheng.module.moment.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Moment {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishDate;
    private List<String> tags;
}
