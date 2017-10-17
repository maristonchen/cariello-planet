package com.mariston.food.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * user
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
public class User implements Serializable{

    /**
     * wechat no
     */
    private String wechatNo;

    /**
     * name
     */
    private String name;

    /**
     * weight
     */
    private String weight;

    /**
     * sex
     */
    private String sex;

    /**
     * birthday
     */
    private String birthday;

    /**
     * body fat percentage
     */
    private String bodyFat;

    /**
     * basal metabolism
     */
    private String metabolism;

    /**
     * the create time of this record
     */
    private String createTime;


    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat;
    }

    public String getMetabolism() {
        return metabolism;
    }

    public void setMetabolism(String metabolism) {
        this.metabolism = metabolism;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
