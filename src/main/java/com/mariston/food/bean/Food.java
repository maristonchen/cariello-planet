package com.mariston.food.bean;

import com.alibaba.fastjson.JSON;

/**
 * food
 * "name": "烧烤（串类）",
 * "calorie": 83.12,
 * "probability": 0.35874313116074
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
public class Food {

    /**
     * food name
     */
    private String name;

    /**
     * food calorie
     */
    private String calorie;

    /**
     * detect probability
     */
    private String probability;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
