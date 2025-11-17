package com.shuiqingzheng.module.article.controller;

import com.shuiqingzheng.common.result.PageResult;
import com.shuiqingzheng.common.result.Result;
import com.shuiqingzheng.module.article.entity.Article;
import com.shuiqingzheng.module.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 获取首页数据
     */
    @GetMapping("/home")
    public Result<Map<String, Object>> getHomeData() {
        return Result.success(articleService.getHomeData());
    }

    /**
     * 获取杂谈列表
     */
    @GetMapping("/articles/essay")
    public Result<PageResult<Article>> getEssays(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "16") Integer size) {
        return Result.success(articleService.getArticles("杂谈", page, size));
    }

    /**
     * 获取技术文章列表
     */
    @GetMapping("/articles/tech")
    public Result<PageResult<Article>> getTechArticles(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "16") Integer size) {
        return Result.success(articleService.getArticles("技术", page, size));
    }

    /**
     * 获取文章详情
     */
    @GetMapping("/articles/{id}")
    public Result<Article> getArticleById(@PathVariable Long id) {
        return Result.success(articleService.getArticleById(id));
    }

    /**
     * 搜索文章
     */
    @GetMapping("/articles/search")
    public Result<PageResult<Article>> searchArticles(
            @RequestParam String q,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(articleService.searchArticles(q, page, size));
    }

    /**
     * 获取所有文章列表（支持分类筛选）
     */
    @GetMapping("/articles")
    public Result<PageResult<Article>> getArticles(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "16") Integer size) {
        return Result.success(articleService.getArticles(category, page, size));
    }
}
