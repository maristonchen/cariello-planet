package com.mariston.food.util;

import com.riversoft.weixin.app.user.SessionKey;
import com.yhxd.tools.base.encrypt.MD5;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * web utils
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
public abstract class WebUtils {


    /**
     * generate an token by session key
     *
     * @param sessionKey session key
     * @return token
     */
    public static String genToken(SessionKey sessionKey) {

        //token salt
        String tokenSalt = RandomStringUtils.random(10) + sessionKey.getOpenId() + sessionKey.getSessionKey();

        return MD5.md5(tokenSalt);
    }
}
