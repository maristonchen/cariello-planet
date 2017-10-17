package com.mariston.food.bean;

import com.alibaba.fastjson.JSON;

/**
 * feeding record
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
public class Feeding {

    /**
     * wechat no
     */
    private String wechatNo;

    /**
     * food name
     */
    private String foodName;

    /**
     * food calorie
     */
    private String calorie;

    /**
     * feeding food time
     */
    private String feedTime;


    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
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
