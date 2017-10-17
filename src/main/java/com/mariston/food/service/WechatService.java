package com.mariston.food.service;

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
     * @return token
     */
    String login(String code);
}
