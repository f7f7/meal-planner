package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.*;
import com.example.mapper.PlanMealMapper;
import com.example.mapper.RecipeMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * service
 **/
@Service
public class PlanMealService {

    @Resource
    private PlanMealMapper planMealMapper;
    @Autowired
    private RecipeMapper recipeMapper;

    public void add(PlanMeal planMeal) {
        planMealMapper.insert(planMeal);
    }

    public void deleteById(Integer id) {
        planMealMapper.deleteById(id);
    }

    public void deleteByPlanId(Integer planId){
        planMealMapper.deleteByPlanId(planId);
    }

    /**
     * delete by batch
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            planMealMapper.deleteById(id);
        }
    }

    public void updateById(PlanMeal planMeal) {
        planMealMapper.updateById(planMeal);
    }

    public PlanMeal selectById(Integer id) {
        return planMealMapper.selectById(id);
    }

    public List<PlanMeal> selectAll(PlanMeal planMeal) {
        return planMealMapper.selectAll(planMeal);
    }

    /**
     * paging query
     */
//    public PageInfo<PlanMeal> selectPage(PlanMeal planMeal, Integer pageNum, Integer pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<PlanMeal> list = planMealMapper.selectAll(planMeal);
//        return PageInfo.of(list);
//    }

    public PageInfo<MealDTO> selectPage(PlanMeal planMeal, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PlanMeal> list = planMealMapper.selectAll(planMeal);
        List<MealDTO> mealDTOList = new ArrayList<>();
        for (PlanMeal planMeal1 : list) {
            mealDTOList.add(convertMealToDTO(planMeal1));
        }
        return PageInfo.of(mealDTOList);
    }


    /**
     * MealDTO -> PlanMeal
     * recipeName -> recipeId
     * */
    public PlanMeal convertDtoToMeal(MealDTO mealDTO) {
        PlanMeal planMeal = planMealMapper.selectById(mealDTO.getId());
        return planMeal;
    }


    /**
     * PlanMeal -> MealDTO
     * recipeId -> recipeName
     * */
    public MealDTO convertMealToDTO(PlanMeal planMeal) {
        MealDTO mealDTO = new MealDTO();
        Recipe recipe = recipeMapper.selectById(planMeal.getRecipeId());
        mealDTO.setId(planMeal.getId());
        mealDTO.setPlanId(planMeal.getPlanId());
        mealDTO.setRecipeName(recipe.getName());
        mealDTO.setDay(planMeal.getDay());
        mealDTO.setType(planMeal.getType());
        mealDTO.setPortion(planMeal.getPortion());
        return mealDTO;
    }
}