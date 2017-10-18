package com.mariston.food.service.impl;

import com.mariston.food.bean.User;
import com.mariston.food.dao.SqliteDao;
import com.mariston.food.service.UserService;
import com.mariston.food.service.WechatService;
import com.riversoft.weixin.app.user.SessionKey;
import com.yhxd.tools.base.date.DateFormatUtil;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
        //set open id
        SessionKey sessionKey = cacheManager.getCache(WechatService.WECHAT_CACHE).get(token, SessionKey.class);
        Assert.notNull(sessionKey, "the token is unused,maybe time out");
        user.setOpenId(sessionKey.getOpenId());
        //set create time
        user.setCreateTime(DateFormatUtil.currentDateTime());
        //save to db
        sqliteDao.insert(user);
    }

    /**
     * query user info
     *
     * @param token wechat login token
     * @return the info of {@link User}
     */
    @Override
    public User query(String token) {
        SessionKey sessionKey = cacheManager.getCache(WechatService.WECHAT_CACHE).get(token, SessionKey.class);
        Assert.notNull(sessionKey, "the token is unused,maybe time out");
        return sqliteDao.selectById(sessionKey.getOpenId(), User.class);
    }
}
