package com.mariston.food.service.impl;

import com.mariston.food.bean.User;
import com.mariston.food.dao.SqliteDao;
import com.mariston.food.service.UserService;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private SqliteDao sqliteDao;

    /**
     * save user info
     *
     * @param token wechat login token
     * @param user  user info
     */
    @Override
    @Transactional
    public void save(String token, User user) {
        // insert into cache
        cacheManager.getCache(USER_CACHE).put(token, user);
        //save to db
        sqliteDao.insert(user);
    }

    /**
     * query user info
     *
     * @param openId wechat no
     * @return the info of {@link User}
     */
    @Override
    public User query(String openId) {
        return sqliteDao.selectById(openId, User.class);
    }
}
