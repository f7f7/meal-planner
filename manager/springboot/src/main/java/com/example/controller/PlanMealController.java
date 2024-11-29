package com.example.controller;

import com.example.common.Result;
import com.example.entity.MealDTO;
import com.example.entity.PlanMeal;
import com.example.service.PlanMealService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * api
 **/
@RestController
@RequestMapping("/planMeal")
public class PlanMealController {

    @Resource
    private PlanMealService planMealService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody PlanMeal planMeal) {
        planMealService.add(planMeal);
        return Result.success();
    }



    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        planMealService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        planMealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody PlanMeal planMeal) {
        planMealService.updateById(planMeal);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        PlanMeal planMeal = planMealService.selectById(id);
        MealDTO mealDTO = planMealService.convertMealToDTO(planMeal);
        return Result.success(mealDTO);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(PlanMeal planMeal ) {
        List<PlanMeal> list = planMealService.selectAll(planMeal);
        List<MealDTO> mealDTOList = new ArrayList<>();
        for (PlanMeal meal : list){
            mealDTOList.add(planMealService.convertMealToDTO(meal));
        }
        return Result.success(mealDTOList);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(PlanMeal planMeal,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<MealDTO> page = planMealService.selectPage(planMeal, pageNum, pageSize);
        return Result.success(page);
    }

}