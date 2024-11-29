package com.example.mapper;

import com.example.entity.PlanMeal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Interfaces for manipulating plan-meal-related data
*/
public interface PlanMealMapper {


    int insert(PlanMeal planMeal);

    int deleteById(Integer id);

    @Delete("DELETE FROM plan_meal WHERE planId = #{planId}")
    void deleteByPlanId(Integer planId);

    int updateById(PlanMeal planMeal);

    PlanMeal selectById(Integer id);

    @Select("SELECT * FROM plan_meal WHERE planId = #{planId}")
    List<PlanMeal> selectByPlanId(@Param("planId") Integer planId);


    List<PlanMeal> selectAll(PlanMeal planMeal);

}