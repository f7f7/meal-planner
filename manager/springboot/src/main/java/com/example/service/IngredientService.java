package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Ingredient;
import com.example.mapper.IngredientMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * transaction of ingredient
 **/
@Service
public class IngredientService {

    @Resource
    private IngredientMapper ingredientMapper;

    /**
     * add
     */
//    public void add(Ingredient ingredient) {
//        ingredientMapper.insert(ingredient);
//    }

    /**
     * only add ingredient that is new in db
     * if ingredient exist, only update other info: price, do not insert new data to db
     * */
    public void add(Ingredient ingredient) {
        Ingredient existingIngregient = ingredientMapper.selectById(ingredient.getId());
        if (existingIngregient != null) {
            existingIngregient.setPrice(ingredient.getPrice());
            ingredientMapper.updateById(existingIngregient);
        }
        ingredientMapper.insert(existingIngregient);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ingredientMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ingredientMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Ingredient ingredient) {
        ingredientMapper.updateById(ingredient);
    }

    /**
     * 根据ID查询
     */
    public Ingredient selectById(Integer id) {
        return ingredientMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Ingredient> selectAll(Ingredient ingredient) {
        return ingredientMapper.selectAll(ingredient);
    }

    /**
     * 分页查询
     */
    public PageInfo<Ingredient> selectPage(Ingredient ingredient, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ingredient> list = ingredientMapper.selectAll(ingredient);
        return PageInfo.of(list);
    }

}