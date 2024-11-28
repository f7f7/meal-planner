package com.example.mapper;

import com.example.entity.Ingredient;

import java.util.List;

/**
 * Interfaces for manipulating ingredient-related data
*/
public interface IngredientMapper {


    int insert(Ingredient ingredient);

    Ingredient selectById(Integer id);

    List<Ingredient> selectAll(Ingredient ingredient);

    Ingredient selectByName(String name) ;

    int updateById(Ingredient ingredient);

    int deleteById(Integer id);


}