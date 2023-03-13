package com.maven.court.dao;

import com.maven.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 这个类就是所有 Dao 实现的基类
 *
 * @param <T> Dao 的实体类的类型
 */
public class BaseDao<T>{

    /**
     *  DBUtils 工具包提供的数据库操作对象
     */

    private final QueryRunner runner = new QueryRunner();

    /**
     * 查询单个对象
     *
     * @param sql 执行查询的 sql 语句
     * @param entityClass 实体类对应的Class 对象
     * @param parameters  传给sql 的语句参数
     * @return 查询到的实体类对象
     */
    public T getSingleBean(String sql,Class<T>entityClass, Object... parameters) {

        try {
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection, sql, new BeanHandler<>(entityClass), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个对象的方法
     *
     * @param sql         执行查询操作的 SQL 语句
     * @param entityClass 实体类的 Class 对象
     * @param parameters  SQL 语句的参数
     * @return 查询结果
     */
    public List<T> getBeanList(String sql, Class<T> entityClass, Object ... parameters) {
        try {
            // 获取数据库连接
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection, sql, new BeanListHandler<>(entityClass), parameters);

        } catch (SQLException e) {
            // 如果真的抛出异常，则将编译时异常封装为运行时异常抛出
           throw  new RuntimeException(e);
        }
    }


    /**
     * 通用的增删改操作 insert delete update 都可以用
     *
     * @param sql 执行查询的 sql 语句
     * @param parameters 传给sql 的语句参数
     * @return 受影响的行数
     */
    public int update(String sql,Object... parameters){

        try {
            Connection connection = JDBCUtils.getConnection();

            int general = runner.update(connection, sql, parameters);
            return general;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
