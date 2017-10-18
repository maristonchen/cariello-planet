package com.mariston.food.service;

import com.mariston.food.bean.Feeding;
import com.mariston.food.bean.Food;

import java.util.List;

/**
 * feed service
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
public interface FeedService {

    /**
     * feeding food
     *
     * @param token login token
     * @param food  food
     */
    void feeding(String token, Food food);

    /**
     * query the list of feeding food
     *
     * @param token   login token
     * @param feeding feeding query condition
     * @return the list of record
     */
    List<Feeding> queryList(String token, Feeding feeding);

    /**
     * query the list of feeding food
     *
     * @param token     login token
     * @param feeding   feeding query condition
     * @param startTime start time
     * @param endTime   end time
     * @return the list of record
     */
    List<Feeding> queryListByTimeRange(String token, Feeding feeding, String startTime, String endTime);
}
