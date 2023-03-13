package com.maven.court.filter;

import com.maven.court.util.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Connection connection = null;
        try{
            //获取数据库链接
             connection = JDBCUtils.getConnection();
            //核心操作
            filterChain.doFilter(servletRequest,servletResponse);
            //提交事务
            connection.commit();
        }catch (Exception e){
            //回滚事务
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            //关闭数据库链接
            JDBCUtils.releaseConnection(connection);
        }
    }




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}
