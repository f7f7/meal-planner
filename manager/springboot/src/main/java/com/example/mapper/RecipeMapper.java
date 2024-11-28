package com.example.mapper;

import com.example.entity.Recipe;

import java.util.List;

/**
 * Interfaces for manipulating recipe-related data
*/
public interface RecipeMapper {


    int insert(Recipe recipe);
    
    int deleteById(Integer id);
    
    int updateById(Recipe recipe);
    
    Recipe selectById(Integer id);
    
    List<Recipe> selectAll(Recipe recipe);

}