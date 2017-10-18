package com.mariston.food.service.impl;

import com.mariston.food.bean.User;
import com.mariston.food.constant.WebConstant;
import com.mariston.food.dao.SqliteDao;
import com.mariston.food.service.WechatService;
import com.mariston.food.util.WebUtils;
import com.riversoft.weixin.app.base.AppSetting;
import com.riversoft.weixin.app.user.SessionKey;
import com.riversoft.weixin.app.user.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * wechat service implement
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService {

    @Value("${wechat.app.id}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    @Resource
    private CacheManager cacheManager;

    @Resource
    private SqliteDao sqliteDao;

    /**
     * wechat login and get session key
     *
     * @param code login code
     * @return token
     */
    @Override
    public Map<String, Object> login(String code) {
        //wechat session_key
        SessionKey sessionKey = Users.with(new AppSetting(appId, secret)).code2Session(code);

        //login token
        String token = WebUtils.genToken(sessionKey);

        //cache login token
        cacheManager.getCache(WECHAT_CACHE).put(WebConstant.WECHAT_LOGIN_TOKEN, sessionKey);

        //check user exist
        User user = sqliteDao.selectById(sessionKey.getOpenId(), User.class);

        Map<String, Object> map = new HashMap<>();
        map.put(WebConstant.WECHAT_LOGIN_TOKEN, token);
        map.put("ifSave", user != null);

        return map;
    }
}
