package com.example.common.enums;

public enum MealTypeEnum {
    TYPE_BREAKFAST("Berakfast"),
    TYPE_LUNCH("Lunch"),
    TYPE_DINNER("Dinner"),
    TYPE_SNACK("Snack")
    ;
    public String type;
    MealTypeEnum(String type){
     this.type = type;
    }
}
