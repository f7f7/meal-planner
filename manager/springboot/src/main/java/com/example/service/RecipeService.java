package com.example.service;

import com.example.entity.*;
import com.example.mapper.IngredientMapper;
import com.example.mapper.RecipeIngredientMapper;
import com.example.mapper.RecipeMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service
 **/
@Service
public class RecipeService {

    @Resource
    private RecipeMapper recipeMapper;
    private IngredientMapper ingredientMapper;
    private RecipeIngredientMapper recipeIngredientMapper;

    private final ObjectMapper mapper = new ObjectMapper(); // Jackson 的 ObjectMapper


    public void add(Recipe recipe) {
        recipeMapper.insert(recipe);
    }

    public void add(RecipeDTO recipeDTO){
        Recipe recipe = convertDtoToRecipe(recipeDTO);

        /**
         * recipe table
         * no ingredients in it
         * */
        recipeMapper.insert(recipe);

        /**
         * 遍历 ingredients
         * */
        recipeDTO.getIngredients().forEach((ingredientName, amount) -> {

            /**
             * ingredient table
             * */
            Ingredient ingredient = ingredientMapper.selectByName(ingredientName);
            if (ingredient == null) {
                ingredient = new Ingredient();
                ingredient.setName(ingredientName);
                ingredientMapper.insert(ingredient);
            }

            /**
             * receipe_ingredient table
             * */
            // 直接通过 Mapper 操作中间表
            recipeIngredientMapper.insert(recipe.getId(), ingredient.getId(), amount);
        });

    }

    public void updateById(Recipe recipe) {
        recipeMapper.updateById(recipe);
    }

    public void updateById(RecipeDTO recipeDTO){
        Recipe recipe = convertDtoToRecipe(recipeDTO);

        recipeMapper.updateById(recipe);
        recipeDTO.getIngredients().forEach((ingredientName, amount) -> {
            Ingredient ingredient = ingredientMapper.selectByName(ingredientName);

            //xxx
            if (ingredient != null) {
                ingredientMapper.updateById(ingredient);
            }

            recipeIngredientMapper.updateById(recipe.getId(), ingredient.getId(), amount);

        });
    }

    /**
     * 删除recipe和它所包含的所有ingredients
     * */
    public void deleteById(Integer id) {
        recipeMapper.deleteById(id);
        recipeIngredientMapper.deleteByRecipeId(id);
    }

    /**
     * delete by batch
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            recipeMapper.deleteById(id);
            recipeIngredientMapper.deleteByRecipeId(id);
        }
    }



    public Recipe selectById(Integer id) {
        return recipeMapper.selectById(id);
    }

    public List<Recipe> selectAll(Recipe recipe) {
        return recipeMapper.selectAll(recipe);
    }

    /**
     * paging query
     */
    public PageInfo<Recipe> selectPage(Recipe recipe, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Recipe> list = recipeMapper.selectAll(recipe);
        return PageInfo.of(list);
    }

    public List<IngredientDTO> selectIngredientDTO(Integer receipeId){
        List<IngredientDTO> list = recipeIngredientMapper.selectByRecipeId(receipeId);
        return list;
    }


/**
 * DTO -> Recipe
 * */
    public Recipe convertDtoToRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setUserId(recipeDTO.getUserId());
        recipe.setAdminId(recipeDTO.getAdminId());

        recipe.setDescription(recipeDTO.getDescription());


        /**
         * Map -> Json
         * steps
         * */
        try {
            String stepsJson = mapper.writeValueAsString(recipeDTO.getSteps());
            recipe.setSteps(stepsJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting mealTimes to JSON", e);
        }

        /**
         * List -> Json
         * tags
         * */
        if (recipeDTO.getTags() != null && !recipeDTO.getTags().isEmpty()) {
            recipe.setTags(String.join(",", recipeDTO.getTags()));
        } else {
            recipe.setTags("");
        }

        return recipe;
    }

    /**
     * Recipe->DTO
     * */
    public RecipeDTO convertRecipeToDto(Recipe recipe, List<IngredientDTO> ingredientDTOList) {
        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setName(recipe.getName());
        recipeDTO.setUserId(recipe.getUserId());
        recipeDTO.setAdminId(recipe.getAdminId());
        recipeDTO.setDescription(recipe.getDescription());

        // JSON -> List
        // of steps
        try {
            Map<Integer,String> steps = (Map<Integer, String>) mapper.readValue(recipe.getSteps(), new TypeReference<List<String>>() {});
            recipeDTO.setSteps(steps);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting steps JSON to List", e);
        }

        // JSON -> List
        // of tags
        if (recipe.getTags() != null && !recipe.getTags().isEmpty()) {
            recipeDTO.setTags(Arrays.asList(recipe.getTags().split(",")));
        }

        //IngredientDTO -> Map
        //of ingredient info, not in recipe table
        Map<String, Integer> ingredientMap = new HashMap<>();
        for(IngredientDTO ingredientDTO : ingredientDTOList){
            ingredientMap.put(ingredientDTO.getName(),ingredientDTO.getAmount());
        }
        recipeDTO.setIngredients(ingredientMap);

        return recipeDTO;

    }
}
