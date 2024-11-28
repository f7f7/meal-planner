package com.example.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Recipe sheet
*/
public class RecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer userId;
    private Integer adminId;
    private Map<String,Integer> ingredients;
    private Map<Integer,String> steps;
    private String description;
    private List<String> tags;

    public RecipeDTO() {
    }

    public RecipeDTO(Recipe recipe) throws JsonProcessingException {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.userId = recipe.getUserId();
        this.adminId = recipe.getAdminId();
//        this.ingredients = new ObjectMapper().readValue(recipe.getIngredients(), new TypeReference<>() {});
        this.steps = new ObjectMapper().readValue(recipe.getSteps(), new TypeReference<>() {});
        this.description = recipe.getDescription();
        this.tags = Arrays.asList(recipe.getTags().split(","));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<Integer, String> getSteps() {
        return steps;
    }

    public void setSteps(Map<Integer, String> steps) {
        this.steps = steps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}