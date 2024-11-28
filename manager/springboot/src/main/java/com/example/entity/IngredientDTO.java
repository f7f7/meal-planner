package com.example.entity;

import java.io.Serializable;

/**
 * IngredientDTO
*/
public class IngredientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer amount;
    private Double price;


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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}