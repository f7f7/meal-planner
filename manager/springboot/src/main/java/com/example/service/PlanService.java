package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Plan;
import com.example.entity.PlanMeal;
import com.example.mapper.PlanMapper;
import com.example.mapper.PlanMealMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * service
 **/
@Service
public class PlanService {

    @Resource
    private PlanMapper planMapper;
    private PlanMealMapper planMealMapper;

    public PlanService(PlanMealMapper planMealMapper) {
        this.planMealMapper = planMealMapper;
    }

    public void add(Plan plan) {
        planMapper.insert(plan);
    }

    public void deleteById(Integer id) {
        /**related meals should be deleted when plan deleted*/
        planMealMapper.deleteByPlanId(id);
        planMapper.deleteById(id);
    }

    /**
     * delete by batch
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            planMealMapper.deleteByPlanId(id);
            planMapper.deleteById(id);
        }
    }

    public void updateById(Plan plan) {
        planMapper.updateById(plan);
    }

    public Plan selectById(Integer id) {
        return planMapper.selectById(id);
    }

    public List<Plan> selectAll(Plan plan) {
        return planMapper.selectAll(plan);
    }

    /**
     * paging query
     */
    public PageInfo<Plan> selectPage(Plan plan, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Plan> list = planMapper.selectAll(plan);
        return PageInfo.of(list);
    }

}