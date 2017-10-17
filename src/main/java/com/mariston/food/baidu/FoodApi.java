package com.mariston.food.baidu;


import com.alibaba.fastjson.JSON;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.mariston.food.bean.Food;
import org.json.JSONArray;
import org.json.JSONObject;
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
        System.out.println(res.toString(2));
        JSONArray array = res.getJSONArray("result");
        if (array != null && array.length() > 0) {
            food = JSON.parseObject(array.getJSONObject(0).toString(), Food.class);
        }

        return food;
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
