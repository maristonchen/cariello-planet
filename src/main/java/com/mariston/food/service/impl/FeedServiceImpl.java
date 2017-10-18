package com.mariston.food.service.impl;

import com.mariston.food.bean.Feeding;
import com.mariston.food.bean.Food;
import com.mariston.food.dao.SqliteDao;
import com.mariston.food.service.FeedService;
import com.mariston.food.service.WechatService;
import com.riversoft.weixin.app.user.SessionKey;
import com.yhxd.tools.base.date.DateFormatUtil;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * feed service implement
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
@Service("feedService")
public class FeedServiceImpl implements FeedService {

    @Resource
    private CacheManager cacheManager;

    @Resource
    private SqliteDao sqliteDao;

    /**
     * feeding food
     *
     * @param token login token
     * @param food  food
     */
    @Override
    @Transactional
    public void feeding(String token, Food food) {

        SessionKey sessionKey = cacheManager.getCache(WechatService.WECHAT_CACHE).get(token, SessionKey.class);
        Assert.notNull(sessionKey, "the token is unused,maybe time out");

        // feeding record
        Feeding feeding = new Feeding();
        feeding.setOpenId(sessionKey.getOpenId());
        feeding.setFoodName(food.getName());
        feeding.setCalorie(food.getCalorie());
        feeding.setFeedTime(DateFormatUtil.currentDateTime());

        //save feeding
        sqliteDao.insert(feeding);

    }

    /**
     * query the list of feeding food
     *
     * @param token   login token
     * @param feeding feeding query condition
     * @return the list of record
     */
    @Override
    public List<Feeding> queryList(String token, Feeding feeding) {
        SessionKey sessionKey = cacheManager.getCache(WechatService.WECHAT_CACHE).get(token, SessionKey.class);
        Assert.notNull(sessionKey, "the token is unused,maybe time out");
        feeding.setOpenId(sessionKey.getOpenId());
        return sqliteDao.selectList(feeding, Feeding.class);
    }

    /**
     * query the list of feeding food
     *
     * @param token     login token
     * @param feeding   feeding query condition
     * @param startTime start time
     * @param endTime   end time
     * @return the list of record
     */
    @Override
    public List<Feeding> queryListByTimeRange(String token, Feeding feeding, String startTime, String endTime) {
        SessionKey sessionKey = cacheManager.getCache(WechatService.WECHAT_CACHE).get(token, SessionKey.class);
        Assert.notNull(sessionKey, "the token is unused,maybe time out");
        feeding.setOpenId(sessionKey.getOpenId());
        return sqliteDao.selectListByTimeRange(feeding, startTime, endTime, Feeding.class);
    }

}
