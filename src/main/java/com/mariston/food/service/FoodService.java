package com.mariston.food.service;


import com.mariston.food.bean.Food;

/**
 * (用一句话描述该文件做什么)
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
public interface FoodService {

    /**
     * food picture detect
     *
     * @param foodImageData food image data
     * @return the info of {@link Food}
     */
    Food detect(byte[] foodImageData);
}
