package com.mariston.food.service;

import java.util.Map;

/**
 * wechat service interface
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
public interface WechatService {

    /**
     * wechat cache name
     */
    String WECHAT_CACHE = "wechat-cache";

    /**
     * wechat login and get session key
     *
     * @param code login code
     * @return token and ifSave
     */
    Map<String,Object> login(String code);
}
