package com.example.mapper;

import com.example.entity.Plan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Interfaces for manipulating plan-related data
*/
public interface PlanMapper {


    int insert(Plan plan);

    int deleteById(Integer id);

    int updateById(Plan plan);

    Plan selectById(Integer id);

    @Select("SELECT * FROM plan WHERE weekStartDate = #{weekStartDate}")
    List<Plan> selectByDate(@Param("weekStartDate") String weekStartDate);


    List<Plan> selectAll(Plan plan);

}