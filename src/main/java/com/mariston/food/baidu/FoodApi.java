package com.mariston.food.baidu;


import com.alibaba.fastjson.JSON;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.mariston.food.bean.Food;
import com.yhxd.tools.base.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;

/**
 * baidu food detect api
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/16
 */
@Service
public class FoodApi implements InitializingBean {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(FoodApi.class);

    /**
     * baidu app id
     */
    @Value("${baidu.app.id}")
    private String appId;

    /**
     * baidu app key
     */
    @Value("${baidu.api.key}")
    private String apiKey;

    /**
     * baidu secret key
     */
    @Value("${baidu.secret.key}")
    private String secretKey;

    /**
     * detect food info by image data
     *
     * @param foodImageData food image data
     * @return the info
     */
    public Food detect(byte[] foodImageData) {

        Food food = null;
        // 初始化一个AipImageClassifyClient
        AipImageClassify client = new AipImageClassify(appId, apiKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理


        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("top_num", "3");

        JSONObject res = client.dishDetect(foodImageData, options);
        logger.info("===菜品识别:\n{}", res.toString(2));
        JSONArray array = res.getJSONArray("result");
        if (array != null && array.length() > 0) {
            food = JSON.parseObject(array.getJSONObject(0).toString(), Food.class);
/*            if (food != null && StringUtils.equals(food.getCalorie(), "0")) {
                JSONObject pres = client.plantDetect(foodImageData, options);
                logger.info("===植物识别:\n{}", pres.toString(2));
                JSONArray parray = pres.getJSONArray("result");
                if (parray != null && array.length() > 0) {
                    JSONObject pobj = parray.getJSONObject(0);
                    String fname = pobj.getString("name");
                    String calorie = fetchCalorie(fname);
                    if (StringUtils.isNotBlank(calorie)) {
                        food.setProbability(pobj.get("score").toString());
                        food.setName(fname);
                        food.setCalorie(calorie);
                    }
                }
            }*/
        }
        return food;
    }

    private String fetchCalorie(String foodName) {
        String url = "http://www.boohee.com/food/search?keyword=" + foodName;

        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsHeadlines = doc.select(".food-list li div p");
            if (CollectionUtil.isNotEmpty(newsHeadlines)) {
                String line = newsHeadlines.get(0).ownText();
                logger.info("薄荷网获取{}→{}", foodName, line);
                return StringUtils.substringBetween(line, "热量：", " 大卡");
            }
        } catch (Exception e) {
            logger.error("fetch calorie occurs an error that is {}-{}", e.getStackTrace()[0], e.getMessage());
        }
        return StringUtils.EMPTY;
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(appId);
        Assert.hasText(apiKey);
        Assert.hasText(secretKey);
    }
}
