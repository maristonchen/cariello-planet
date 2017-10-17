package com.mariston.food.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mariston.food.constant.WebConstant;
import com.mariston.food.service.WechatService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * wechat controller
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    /**
     * wechat service
     */
    @Resource
    private WechatService wechatService;


    /**
     * wechat login
     *
     * @param request request
     * @return result
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String login(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();

        try {
            //get code
            String code = request.getParameter("code");

            //login and get token
            String token = wechatService.login(code);

            //save the token in session
            WebUtils.setSessionAttribute(request, WebConstant.WECHAT_LOGIN_TOKEN, token);

            map.put("result", StringUtils.isNotBlank(token) ? 1 : 0);

            map.put("token", token);
        } catch (Exception e) {
            logger.error("wechat login occur an error that is {}-{}", e.getStackTrace()[0], e.getMessage());
            map.put("result", 0);
            map.put("errMsg", e.getMessage());
        }

        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
