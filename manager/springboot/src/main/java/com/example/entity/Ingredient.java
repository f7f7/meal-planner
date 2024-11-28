package com.example.entity;

import java.io.Serializable;

/**
 * Ingredient sheet
*/
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}