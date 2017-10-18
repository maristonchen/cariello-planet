package com.mariston.food.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mariston.food.bean.Feeding;
import com.mariston.food.bean.Food;
import com.mariston.food.constant.WebConstant;
import com.mariston.food.service.FeedService;
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
import java.util.List;
import java.util.Map;

/**
 * feeding controller
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
@RequestMapping("/feed")
@Controller
public class FeedController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(FeedController.class);

    /**
     * feed service
     */
    @Resource
    private FeedService feedService;

    /**
     * feeding food
     *
     * @param request request
     * @param food    food
     * @return result
     */
    @RequestMapping(value = "/do", method = RequestMethod.POST, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String feed(HttpServletRequest request, Food food) {

        Map<String, Object> map = new HashMap<>();
        try {
            String token = request.getParameter(WebConstant.WECHAT_LOGIN_TOKEN);
            Assert.hasText(token, "login token is empty or null");
            feedService.feeding(token, food);
            map.put("result", 1);
        } catch (Exception e) {
            logger.error("feeding food occurs an error that is {}-{}", e.getStackTrace()[0], e.getMessage());
            map.put("result", 0);
            map.put("errMsg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * query the list of feeding record
     *
     * @param openId wechat open id
     * @return result
     */
    @RequestMapping(value = "/queryList", method = RequestMethod.POST, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String queryList(Feeding feeding) {

        Map<String, Object> map = new HashMap<>();
        try {
            Assert.notNull(feeding, "query condition is null");
            List<Feeding> feedings = feedService.queryList(feeding);
            map.put("result", 1);
            map.put("data", feedings);
        } catch (Exception e) {
            logger.error("Querying the list of feeding food occurs an error that is {}-{}", e.getStackTrace()[0], e.getMessage());
            map.put("result", 0);
            map.put("errMsg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
