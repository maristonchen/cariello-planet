package com.mariston.food.service.impl;

import com.mariston.food.baidu.FoodApi;
import com.mariston.food.bean.Food;
import com.mariston.food.service.FoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * food service implement
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Service("foodService")
public class FoodServiceImpl implements FoodService {

    @Resource
    private FoodApi foodApi;

    /**
     * food picture detect
     *
     * @param foodImageData food image data
     * @return the info of {@link Food}
     */
    @Override
    public Food detect(byte[] foodImageData) {
        return foodApi.detect(foodImageData);
    }
}
