package com.mariston.food.dao;

import java.io.Serializable;
import java.util.List;

/**
 * sqlite dao
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
public interface SqliteDao {

    /**
     * insert data
     *
     * @param t data
     */
    <T> void insert(T t);

    /**
     * update data
     *
     * @param t data
     */
    <T> void update(T t);

    /**
     * select data by id
     *
     * @param id    id
     * @param clazz class
     * @param <T>   type
     * @return data
     */
    <T> T selectById(Serializable id, Class<T> clazz);


    /**
     * select the list of data
     *
     * @param t     object
     * @param clazz class
     * @param <T>   type
     * @return list
     */
    <T> List<T> selectList(T t, Class<T> clazz);

    /**
     * select the list of data
     *
     * @param t         object
     * @param startTime start time
     * @param endTime   end time
     * @param clazz     class
     * @param <T>       type
     * @return list
     */
    <T> List<T> selectListByTimeRange(T t, String startTime, String endTime, Class<T> clazz);
}
