package com.mariston.food.bean;

import com.alibaba.fastjson.JSON;
import com.mariston.food.annotation.DataType;
import com.mariston.food.annotation.Table;

import java.io.Serializable;

/**
 * user info
 * <p>
 * wechat userinfo:
 * <p>
 * {
 * "openId": "OPENID",
 * "nickName": "NICKNAME",
 * "gender": GENDER,
 * "city": "CITY",
 * "province": "PROVINCE",
 * "country": "COUNTRY",
 * "avatarUrl": "AVATARURL",
 * "unionId": "UNIONID",
 * "watermark":
 * {
 * "appid":"APPID",
 * "timestamp":TIMESTAMP
 * }
 * }
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Table(name = "t_user",key = "openId",time = "createTime")
public class User implements Serializable {

    private static final long serialVersionUID = -3683400018270846391L;
    /**
     * wechat open id
     */
    @DataType
    private String openId;

    /**
     * nick name
     */
    @DataType
    private String nickName;

    /**
     * weight
     */
    @DataType
    private String weight;

    /**
     * gender
     */
    @DataType
    private String gender;

    /**
     * birthday
     */
    @DataType
    private String birthday;

    /**
     * body fat percentage
     */
    @DataType
    private String bodyFat;

    /**
     * basal metabolism
     */
    @DataType
    private String metabolism;

    /**
     * city
     */
    @DataType
    private String city;

    /**
     * province
     */
    @DataType
    private String province;

    /**
     * country
     */
    @DataType
    private String country;

    /**
     * avatarUrl
     */
    @DataType
    private String avatarUrl;

    /**
     * the create time of this record
     */
    @DataType
    private String createTime;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
