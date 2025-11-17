package com.shuiqingzheng.module.recommend.mapper;

import com.shuiqingzheng.module.recommend.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendMapper {

    void insert(Recommend recommend);

    void update(Recommend recommend);

    void deleteById(Long id);

    Recommend selectById(Long id);

    List<Recommend> selectPage(@Param("type") String type,
                                @Param("keyword") String keyword,
                                @Param("offset") Integer offset,
                                @Param("size") Integer size);

    Long countRecommends(@Param("type") String type,
                         @Param("keyword") String keyword);
}
