package com.example.mapper;

import com.example.entity.IngredientDTO;
import com.example.entity.Recipe;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Interfaces for manipulating recipeIngredient-related data
*/
public interface RecipeIngredientMapper {
    /**
     * Insert
     */
    @Insert("INSERT INTO recipe_ingredient (recipeId, ingredientId, amount) VALUES (#{recipeId}, #{ingredientId}, #{amount})")
    int insert(@Param("recipeId") Integer recipeId, @Param("ingredientId") Integer ingredientId, @Param("amount") Integer amount);

    /**
     * delete by id
     * */
    @Delete("DELETE FROM recipe_ingredient WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);


//    /**
//     * 根据 Recipe ID 和 Ingredient ID 删除特定的关联记录
//     */
//    @Delete("DELETE FROM recipe_ingredient WHERE recipeId = #{recipeId} AND ingredientId = #{ingredientId}")
//    int deleteByRecipeAndIngredientId(@Param("recipeId") Integer recipeId, @Param("ingredientId") Integer ingredientId);

    /**
     * 根据 Recipe ID 删除所有关联的 Ingredient 记录
     */
    @Delete("DELETE FROM recipe_ingredient WHERE recipeId = #{recipeId}")
    int deleteByRecipeId(@Param("recipeId") Integer recipeId);


    /**
     * update by id
     * (only amount can be updated)
     */
    @Update("UPDATE recipe_ingredient SET amount = #{amount} WHERE recipeId = #{recipeId} AND ingredientId = #{ingredientId}")
    int updateById(@Param("recipeId") Integer recipeId, @Param("ingredientId") Integer ingredientId, @Param("amount") Integer amount);


    /**
     * xxx
     * update name and amount
     * */
    @Update("UPDATE recipe_ingredient ri " +
            "JOIN ingredient i ON ri.ingredientId = i.id " +
            "SET ri.amount = #{amount}, ri.ingredientId " +
            "WHERE ri.recipeId = #{recipeId} AND ri.ingredientId = #{ingredientId}")
    int updateNameAndAmount (@Param("recipeId") Integer recipeId,
                              @Param("ingredientId") Integer ingredientId,
                              @Param("amount") Integer amount,
                              @Param("name") String name);


    /**
     * select IngredientDTO
     */
    @Select("SELECT i.id, i.name, ri.amount, i.price FROM recipe_ingredient ri " +
            "JOIN ingredient i ON ri.ingredientId = i.id " +
            "WHERE ri.recipeId = #{recipeId}")
    List<IngredientDTO> selectByRecipeId(@Param("recipeId") Integer recipeId);


    /**
     * xxx
     * select by id
     * */
//    @Select("SELECT * FROM recipe_ingredient WHERE id = #{id}")


    /**
     * xxx
     * 根据 Ingredient ID 查询所有关联的 Recipe
     */
    @Select("SELECT r.id, r.name FROM recipe_ingredient ri " +
            "JOIN recipe r ON ri.recipeId = r.id " +
            "WHERE ri.ingredientId = #{ingredientId}")
    List<Recipe> selectRecipesByIngredientId(@Param("ingredientId") Integer ingredientId);
}

