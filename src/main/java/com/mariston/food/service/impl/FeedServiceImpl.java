package com.mariston.food.service.impl;

import com.mariston.food.bean.Feeding;
import com.mariston.food.bean.Food;
import com.mariston.food.bean.User;
import com.mariston.food.service.FeedService;
import com.mariston.food.service.UserService;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * (用一句话描述该文件做什么)
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
@Service("feedService")
public class FeedServiceImpl implements FeedService {

    @Resource
    private CacheManager cacheManager;

    /**
     * feeding food
     *
     * @param token login token
     * @param food  food
     */
    @Override
    public void feeding(String token, Food food) {

        User user = cacheManager.getCache(UserService.USER_CACHE).get(token, User.class);
        Assert.notNull(user,"user is null");

    }

    /**
     * query the list of feeding food
     *
     * @param wechatNo wechat no
     * @return the list of record
     */
    @Override
    public List<Feeding> queryList(String wechatNo) {
        return null;
    }
}
