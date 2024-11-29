package com.example.mapper;

import com.example.entity.Plan;

import java.util.List;

/**
 * Interfaces for manipulating plan-related data
*/
public interface PlanMapper {


    int insert(Plan plan);

    int deleteById(Integer id);

    int updateById(Plan plan);

    Plan selectById(Integer id);

    List<Plan> selectAll(Plan plan);

}