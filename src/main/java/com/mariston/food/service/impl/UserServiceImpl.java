package com.mariston.food.service.impl;

import com.mariston.food.bean.User;
import com.mariston.food.service.UserService;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * user service implement
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private CacheManager cacheManager;

    /**
     * save user info
     *
     * @param token wechat login token
     * @param user  user info
     */
    @Override
    public void save(String token, User user) {

        cacheManager.getCache(USER_CACHE).put(token,user);

    }

    /**
     * query user info
     *
     * @param wechatNo wechat no
     * @return the info of {@link User}
     */
    @Override
    public User query(String wechatNo) {
        return cacheManager.getCache(USER_CACHE).get(wechatNo,User.class);
    }
}
