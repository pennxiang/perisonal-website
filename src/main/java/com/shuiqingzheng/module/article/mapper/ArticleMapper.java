package com.shuiqingzheng.module.article.mapper;

import com.shuiqingzheng.module.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 插入文章
     */
    void insert(Article article);

    /**
     * 更新文章
     */
    void update(Article article);

    /**
     * 根据ID删除文章
     */
    void deleteById(Long id);

    /**
     * 根据ID查询文章
     */
    Article selectById(Long id);

    /**
     * 分页查询文章列表
     */
    List<Article> selectPage(@Param("category") String category,
                             @Param("status") String status,
                             @Param("keyword") String keyword,
                             @Param("offset") Integer offset,
                             @Param("size") Integer size);

    /**
     * 查询文章总数
     */
    Long countArticles(@Param("category") String category,
                       @Param("status") String status,
                       @Param("keyword") String keyword);

    /**
     * 查询最新文章
     */
    Article selectLatest();

    /**
     * 按分类查询前N篇文章
     */
    List<Article> selectTopByCategory(@Param("category") String category,
                                       @Param("limit") Integer limit);

    /**
     * 增加浏览数
     */
    void incrementViewCount(Long id);

    /**
     * 搜索文章
     */
    List<Article> searchArticles(@Param("keyword") String keyword,
                                  @Param("offset") Integer offset,
                                  @Param("size") Integer size);

    /**
     * 搜索文章总数
     */
    Long countSearchArticles(@Param("keyword") String keyword);
}
