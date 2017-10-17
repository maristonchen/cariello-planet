package com.mariston.food.service;

import com.mariston.food.bean.User;

/**
 * User service
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
public interface UserService {

    /**
     * user cache name
     */
    String USER_CACHE = "user-cache";

    /**
     * save user info
     *
     * @param token wechat login token
     * @param user user info
     */
    void save(String token,User user);

    /**
     * query user info
     *
     * @param wechatNo wechat no
     * @return the info of {@link User}
     */
    User query(String wechatNo);
}
