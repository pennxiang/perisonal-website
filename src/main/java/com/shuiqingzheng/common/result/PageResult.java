package com.shuiqingzheng.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private List<T> content;
    private Integer page;
    private Integer size;
    private Long total;
    private Integer totalPages;

    public static <T> PageResult<T> of(List<T> content, Integer page, Integer size, Long total) {
        int totalPages = (int) Math.ceil((double) total / size);
        return new PageResult<>(content, page, size, total, totalPages);
    }
}
