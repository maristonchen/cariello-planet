package com.mariston.food.dao.impl;

import com.mariston.food.dao.SqliteDao;
import com.mariston.food.util.SQLUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (用一句话描述该文件做什么)
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
@Repository("sqliteDao")
public class SqliteDaoImpl implements SqliteDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * insert data
     *
     * @param t data
     */
    @Override
    public <T> void insert(T t) {
        Assert.notNull(t, "insert object is null");
        String sql = SQLUtils.genInsertSQL(t);
        Assert.hasText(sql, "sql is empty or null");
        jdbcTemplate.execute(sql);
    }

    /**
     * update data
     *
     * @param t data
     */
    @Override
    public <T> void update(T t) {
        Assert.notNull(t, "update object is null");
        String sql = SQLUtils.genInsertSQL(t);
        Assert.hasText(sql, "sql is empty or null");
        jdbcTemplate.update(sql);
    }

    /**
     * select data by id
     *
     * @param id    id
     * @param clazz class
     * @return data
     */
    @Override
    public <T> T selectById(Serializable id, Class<T> clazz) {
        Assert.notNull(id, "id is null");
        String sql = SQLUtils.genSelectOneSQL(id, clazz);
        Assert.hasText(sql, "sql is empty or null");
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    /**
     * select the list of data
     *
     * @param t     object
     * @param clazz class
     * @return list
     */
    @Override
    public <T> List<T> selectList(T t, Class<T> clazz) {
        String sql = SQLUtils.genSelectListSQL(t, clazz);
        Assert.hasText(sql, "sql is empty or null");
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * select the list of data
     *
     * @param t         object
     * @param startTime start time
     * @param endTime   end time
     * @param clazz     class
     * @return list
     */
    @Override
    public <T> List<T> selectListByTimeRange(T t, String startTime, String endTime, Class<T> clazz) {
        String sql = SQLUtils.genSelectListByTimeRangeSQL(t, startTime, endTime, clazz);
        Assert.hasText(sql, "sql is empty or null");
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
