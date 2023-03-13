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
            //��ȡ���ݿ�����
             connection = JDBCUtils.getConnection();
            //���Ĳ���
            filterChain.doFilter(servletRequest,servletResponse);
            //�ύ����
            connection.commit();
        }catch (Exception e){
            //�ع�����
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            //�ر����ݿ�����
            JDBCUtils.releaseConnection(connection);
        }
    }




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}
