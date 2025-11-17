package com.shuiqingzheng.module.article.service;

import com.shuiqingzheng.common.result.PageResult;
import com.shuiqingzheng.module.article.entity.Article;
import com.shuiqingzheng.module.article.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;

    /**
     * 获取首页数据
     */
    public Map<String, Object> getHomeData() {
        Map<String, Object> result = new HashMap<>();
        result.put("latestArticle", articleMapper.selectLatest());
        result.put("essays", articleMapper.selectTopByCategory("杂谈", 3));
        result.put("techArticles", articleMapper.selectTopByCategory("技术", 3));
        // 瞬间数据需要从MomentService获取
        return result;
    }

    /**
     * 分页查询文章列表
     */
    public PageResult<Article> getArticles(String category, Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Article> articles = articleMapper.selectPage(category, "published", null, offset, size);
        Long total = articleMapper.countArticles(category, "published", null);
        return PageResult.of(articles, page, size, total);
    }

    /**
     * 根据ID获取文章详情
     */
    public Article getArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article != null) {
            articleMapper.incrementViewCount(id);
        }
        return article;
    }

    /**
     * 搜索文章
     */
    public PageResult<Article> searchArticles(String keyword, Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Article> articles = articleMapper.searchArticles(keyword, offset, size);
        Long total = articleMapper.countSearchArticles(keyword);
        return PageResult.of(articles, page, size, total);
    }

    /**
     * 创建文章
     */
    @Transactional
    public Article createArticle(Article article) {
        article.setPublishDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        articleMapper.insert(article);
        return article;
    }

    /**
     * 更新文章
     */
    @Transactional
    public void updateArticle(Long id, Article article) {
        article.setId(id);
        article.setUpdateDate(LocalDateTime.now());
        articleMapper.update(article);
    }

    /**
     * 删除文章
     */
    @Transactional
    public void deleteArticle(Long id) {
        articleMapper.deleteById(id);
    }

    /**
     * 管理后台：分页查询所有文章（包括草稿）
     */
    public PageResult<Article> getAdminArticles(String status, String category, String keyword, Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Article> articles = articleMapper.selectPage(category, status, keyword, offset, size);
        Long total = articleMapper.countArticles(category, status, keyword);
        return PageResult.of(articles, page, size, total);
    }
}
