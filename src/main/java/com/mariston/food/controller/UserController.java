package com.mariston.food.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mariston.food.bean.User;
import com.mariston.food.constant.WebConstant;
import com.mariston.food.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * User Controller
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@RequestMapping("/user")
@Controller
public class UserController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * user service
     */
    @Resource
    private UserService userService;

    /**
     * save user info
     *
     * @param user user info
     * @return result
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String save(HttpServletRequest request, User user) {

        Map<String, Object> map = new HashMap<>();

        try {
            String token = request.getParameter(WebConstant.WECHAT_LOGIN_TOKEN);
            Assert.hasText(token,"login token is empty or null");
            Assert.notNull(user,"the info of user is null");
            userService.save(token,user);
            map.put("result", 1);
        } catch (Exception e) {
            logger.error("saving user info occur an error that is {}-{}", e.getStackTrace()[0], e.getMessage());
            map.put("result", 0);
            map.put("errMsg", e.getMessage());
        }

        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * query user info
     *
     * @param openId wechat open id
     * @return result
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String query(String openId) {

        Map<String, Object> map = new HashMap<>();

        try {
            User user = userService.query(openId);
            map.put("result", user == null ? 0 : 1);
            map.put("user", user);
        } catch (Exception e) {
            logger.error("querying the user info by openId, occurs an error that is {}-{}", e.getStackTrace()[0], e.getMessage());
            map.put("result", 0);
            map.put("errMsg", e.getMessage());
        }

        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

}
