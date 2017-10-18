package com.mariston.food.service.impl;

import com.mariston.food.constant.WebConstant;
import com.mariston.food.service.WechatService;
import com.mariston.food.util.WebUtils;
import com.riversoft.weixin.app.base.AppSetting;
import com.riversoft.weixin.app.user.SessionKey;
import com.riversoft.weixin.app.user.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * wechat service implement
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService {

    @Resource
    private CacheManager cacheManager;

    @Value("${wechat.app.id}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    /**
     * wechat login and get session key
     *
     * @param code login code
     * @return token
     */
    @Override
    public String login(String code) {
        //wechat session_key
        SessionKey sessionKey = Users.with(new AppSetting(appId, secret)).code2Session(code);

        //login token
        String token = WebUtils.genToken(sessionKey);

        //cache login token
        cacheManager.getCache(WECHAT_CACHE).put(WebConstant.WECHAT_LOGIN_TOKEN, sessionKey);

        return token;
    }
}
