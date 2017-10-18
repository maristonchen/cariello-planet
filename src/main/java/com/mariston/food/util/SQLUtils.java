package com.mariston.food.util;

import com.mariston.food.annotation.DataType;
import com.mariston.food.annotation.Table;
import com.mariston.food.bean.User;
import com.yhxd.tools.base.object.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * (用一句话描述该文件做什么)
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
public abstract class SQLUtils {

    public static <T> String genCreateSQL(Class<T> clazz) {

        try {
            Table table = clazz.getAnnotation(Table.class);

            StringBuilder sql = new StringBuilder();
            sql.append(" create table ").append(table.name()).append(" ( ");

            Field[] fields = clazz.getDeclaredFields();

            StringBuilder fieldNames = new StringBuilder();
            for (Field field : fields) {

                if (!StringUtils.equals(field.getName(), "serialVersionUID")) {
                    DataType dataType = field.getAnnotation(DataType.class);
                    if (StringUtils.equals(field.getName(), table.key())) {
                        fieldNames.append(",").append(field.getName()).append(StringUtils.SPACE).append(dataType.value()).append(" primary key ");
                    } else {
                        fieldNames.append(",").append(field.getName()).append(StringUtils.SPACE).append(dataType.value());
                    }
                }
            }
            sql.append(fieldNames.substring(1)).append(" ) ");

            return sql.toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }

    }

    public static <T> String genInsertSQL(T t) {

        try {
            Table table = t.getClass().getAnnotation(Table.class);

            StringBuilder sql = new StringBuilder();
            sql.append(" insert into ").append(table.name()).append(" ( ");

            StringBuilder fieldNames = new StringBuilder();
            StringBuilder fieldValues = new StringBuilder();

            Map<String, String> map = BeanUtil.describe(t);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!StringUtils.equals(entry.getKey(), "class") && StringUtils.isNotBlank(entry.getValue())) {
                    fieldNames.append(",").append(entry.getKey());
                    fieldValues.append(",").append("'").append(entry.getValue()).append("'");
                }
            }

            sql.append(fieldNames.substring(1)).append(" ) values (");

            sql.append(fieldValues.substring(1)).append(" ) ");

            return sql.toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }

    }


    public static <T> String genUpdateSQL(T t) {

        try {
            Table table = t.getClass().getAnnotation(Table.class);

            StringBuilder sql = new StringBuilder();
            sql.append(" update ").append(table.name()).append(" set ");

            Map<String, String> map = BeanUtil.describe(t);

            StringBuilder kvs = new StringBuilder();

            String where = StringUtils.EMPTY;

            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!StringUtils.equals(entry.getKey(), "class") && StringUtils.isNotBlank(entry.getValue())) {
                    if (StringUtils.equals(entry.getKey(), table.key())) {
                        where = entry.getValue();
                    } else {
                        kvs.append(",").append(entry.getKey()).append("=").append("'").append(entry.getValue()).append("'");
                    }
                }
            }

            sql.append(kvs.substring(1)).append(" where ");

            sql.append(table.key()).append("=").append("'").append(where).append("'");

            return sql.toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }

    }

    public static <T> String genSelectOneSQL(Serializable id, Class<T> clazz) {
        try {
            Table table = clazz.getAnnotation(Table.class);
            return " select * from " + table.name() + " where " + table.key() + "='" + id + "'";
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static <T> String genSelectListSQL(T t,Class<T> clazz) {
        try {
            Table table = clazz.getAnnotation(Table.class);

            StringBuilder sql = new StringBuilder();
            sql.append(" select * from ").append(table.name()).append(" where 1=1 ");

            if (t != null) {
                Map<String, String> map = BeanUtil.describe(t);

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (!StringUtils.equals(entry.getKey(), "class") && StringUtils.isNotBlank(entry.getValue())) {
                        sql.append(" and ").append(entry.getKey()).append("=").append("'").append(entry.getValue()).append("'");
                    }
                }
            }
            return sql.toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * generate select list by time range sql
     *
     * @param t         object
     * @param startTime start time
     * @param endTime   end time
     * @param clazz   class
     * @param <T>       type
     * @return sql
     */
    public static <T> String genSelectListByTimeRangeSQL(T t, String startTime, String endTime,Class<T> clazz) {
        try {
            Table table = clazz.getAnnotation(Table.class);

            StringBuilder sql = new StringBuilder();
            sql.append(" select * from ").append(table.name()).append(" where 1=1 ");

            //query condition
            if (t != null) {
                Map<String, String> map = BeanUtil.describe(t);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (!StringUtils.equals(entry.getKey(), table.time())&&!StringUtils.equals(entry.getKey(), "class") && StringUtils.isNotBlank(entry.getValue())) {
                        sql.append(" and ").append(entry.getKey()).append("=").append("'").append(entry.getValue()).append("'");
                    }
                }
            }

            //time range
            if (StringUtils.isNotBlank(startTime)) {
                sql.append(" and ").append(table.time()).append(">=").append("'").append(startTime).append("'");
            }
            if (StringUtils.isNotBlank(endTime)) {
                sql.append(" and ").append(table.time()).append("<").append("'").append(endTime).append("'");
            }

            return sql.toString();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setOpenId("4444");
        user.setAvatarUrl("343434");
        user.setBirthday("2013-09-09");
        user.setGender("0");
        user.setCreateTime("2017-10-18 10:15:02");

        System.out.println(genCreateSQL(User.class));
        System.out.println(genInsertSQL(user));
        System.out.println(genUpdateSQL(user));
        System.out.println(genSelectListSQL(user,User.class));
        System.out.println(genSelectListByTimeRangeSQL(null, "2017-10-18 10:15:02", "2017-10-18 15:15:02",User.class));
    }
}
