package com.example.controller;

import com.example.common.Result;
import com.example.entity.Ingredient;
import com.example.service.IngredientService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * api for ingredient
 **/
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Resource
    private IngredientService ingredientService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Ingredient ingredient) {
        ingredientService.add(ingredient);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        ingredientService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ingredientService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Ingredient ingredient) {
        ingredientService.updateById(ingredient);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.selectById(id);
        return Result.success(ingredient);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Ingredient ingredient ) {
        List<Ingredient> list = ingredientService.selectAll(ingredient);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Ingredient ingredient,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Ingredient> page = ingredientService.selectPage(ingredient, pageNum, pageSize);
        return Result.success(page);
    }

}