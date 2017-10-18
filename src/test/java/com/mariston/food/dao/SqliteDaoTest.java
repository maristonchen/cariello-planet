package com.mariston.food.dao;

import com.mariston.food.bean.Feeding;
import com.yhxd.tools.base.date.DateFormatUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * (用一句话描述该文件做什么)
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-global.xml")
public class SqliteDaoTest {

    @Resource
    private SqliteDao sqliteDao;

    @Test
    public void insert() throws Exception {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(10000);
            Feeding feeding = new Feeding();
            feeding.setOpenId(RandomStringUtils.random(6,false,true));
            feeding.setFoodName("水果沙拉"+i);
            feeding.setCalorie(RandomStringUtils.random(4,false,true));
            feeding.setFeedTime(DateFormatUtil.currentDateTime());
            sqliteDao.insert(feeding);
        }
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
        Feeding feeding = sqliteDao.selectById("652192", Feeding.class);
        System.out.println(feeding);
    }

    @Test
    public void selectList() throws Exception {
        Feeding feeding = new Feeding();
        List<Feeding> feedings = sqliteDao.selectListByTimeRange(feeding, "2017-10-18 11:09:21", "2017-10-18 11:11:21", Feeding.class);
        System.out.println(feedings);
    }

}