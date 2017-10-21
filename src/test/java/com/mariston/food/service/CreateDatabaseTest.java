package com.mariston.food.service;

import com.mariston.food.bean.Feeding;
import com.mariston.food.bean.User;
import com.mariston.food.util.SQLUtils;
import com.yhxd.tools.base.date.DateFormatUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * (用一句话描述该文件做什么)
 *
 * @author mariston
 * @version V1.0
 * @since 2017/08/14
 */
public class CreateDatabaseTest {


    @Test
    public void init() {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:planet.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

//            statement.executeUpdate("drop table if exists t_feeding");
//            statement.executeUpdate("drop table if exists t_user");
//            statement.executeUpdate(SQLUtils.genCreateSQL(User.class));
//            statement.executeUpdate(SQLUtils.genCreateSQL(Feeding.class));

//            User user = new User();
//            user.setOpenId("4444");
//            user.setAvatarUrl("343434");
//            user.setBirthday("2013-09-09");
//            user.setGender("0");
//            statement.executeUpdate(SQLUtils.genInsertSQL(user));

            Feeding feeding = new Feeding();
            feeding.setFeedId(RandomStringUtils.random(14,true,true));
            feeding.setOpenId("2342342342342");
            feeding.setFoodName("sand");
            feeding.setCalorie("242");
            feeding.setFeedTime(DateFormatUtil.currentDateTime());
            statement.executeUpdate(SQLUtils.genInsertSQL(feeding));

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    @Test
    public void fetchCal() {

        String url = "http://www.boohee.com/food/search?keyword=踢脚线" ;

        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsHeadlines = doc.select(".food-list li div p");
            for (Element line : newsHeadlines) {
                System.out.println(line.ownText());
            }
            System.out.println(newsHeadlines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}