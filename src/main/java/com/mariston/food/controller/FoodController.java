package com.mariston.food.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mariston.food.bean.Food;
import com.mariston.food.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Food Controller
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Controller
@RequestMapping("/food")
public class FoodController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(FoodController.class);

    /**
     * food service
     */
    @Resource
    private FoodService foodService;

    /**
     * 食物图片上传识别
     *
     * @param request
     * @return { result: 1- success 0 - fail ,data:{ detect info }}
     */
    @RequestMapping(value = "/detect", method = RequestMethod.POST, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String detect(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        try {
            //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    //记录上传过程起始时的时间，用来计算上传时间
                    int pre = (int) System.currentTimeMillis();
                    //取得上传文件
                    MultipartFile file = multiRequest.getFile(iter.next());

                    if (file != null) {
                        Food food = foodService.detect(file.getBytes());
                        map.put("result", food == null ? 0 : 1);
                        map.put("data", food);
                    } else {
                        map.put("result", 0);
                        map.put("errMsg", "Can't get the file");
                    }
                    //记录上传该文件后的时间
                    int finaltime = (int) System.currentTimeMillis();
                    System.out.println(finaltime - pre);
                }
            }
        } catch (Exception e) {
            logger.error("food detect fail,the error is {}-{}", e.getStackTrace()[0], e.getMessage());
            map.put("result", 0);
            map.put("errMsg", e.getMessage());
        }
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);

    }


}
