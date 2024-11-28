package com.example.controller;

import com.example.common.Result;
import com.example.entity.IngredientDTO;
import com.example.entity.Recipe;
import com.example.entity.RecipeDTO;
import com.example.service.RecipeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * api for recipe
 **/
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Resource
    private RecipeService recipeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody RecipeDTO recipeDTO) {
        recipeService.add(recipeDTO);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        recipeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        recipeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody RecipeDTO recipeDTO) {
        recipeService.updateById(recipeDTO);
        return Result.success();
    }

    /**
     * select DTO by id
     * DTO contains list of ingredients
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Recipe recipe = recipeService.selectById(id);
        List<IngredientDTO> ingredientDTOList = recipeService.selectIngredientDTO(recipe.getId());
        RecipeDTO recipeDTO = recipeService.convertRecipeToDto(recipe,ingredientDTOList);
        return Result.success(recipeDTO);
    }

    /**
     * select all
     * including ingredients info
     */
    @GetMapping("/selectAll")
    public Result selectAll(RecipeDTO recipeDTO) {
        List<RecipeDTO> list = recipeService.selectAll(recipeDTO);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(RecipeDTO recipeDTO,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
//        Recipe recipe = recipeService.convertDtoToRecipe(recipeDTO);
        PageInfo<RecipeDTO> page = recipeService.selectPage(recipeDTO, pageNum, pageSize);
        return Result.success(page);
    }

}