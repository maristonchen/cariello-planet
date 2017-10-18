package com.mariston.food.bean;

import com.alibaba.fastjson.JSON;
import com.mariston.food.annotation.DataType;
import com.mariston.food.annotation.Table;

import java.io.Serializable;

/**
 * feeding record
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Table(name = "t_feeding",key = "feedId",time = "feedTime")
public class Feeding implements Serializable {

    private static final long serialVersionUID = 5921578438348825598L;

    /**
     * feed primary key
     */
    @DataType
    private String feedId;

    /**
     * wechat open id
     */
    @DataType
    private String openId;

    /**
     * food name
     */
    @DataType
    private String foodName;

    /**
     * food calorie
     */
    @DataType
    private String calorie;

    /**
     * feeding food time
     */
    @DataType
    private String feedTime;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(String feedTime) {
        this.feedTime = feedTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
