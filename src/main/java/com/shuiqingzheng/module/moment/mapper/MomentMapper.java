package com.shuiqingzheng.module.moment.mapper;

import com.shuiqingzheng.module.moment.entity.Moment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MomentMapper {

    void insert(Moment moment);

    void update(Moment moment);

    void deleteById(Long id);

    Moment selectById(Long id);

    List<Moment> selectPage(@Param("keyword") String keyword,
                            @Param("offset") Integer offset,
                            @Param("size") Integer size);

    Long countMoments(@Param("keyword") String keyword);

    /**
     * 查询首页展示的前N条瞬间
     */
    List<Moment> selectTop(@Param("limit") Integer limit);
}
