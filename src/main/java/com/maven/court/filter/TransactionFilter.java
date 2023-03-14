package com.maven.court.filter;

import com.maven.court.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TransactionFilter implements Filter {


    // �������ϱ��澲̬��Դ��չ��
    private static Set<String>  staticResourceExtNameSet;

    static {
        staticResourceExtNameSet = new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jpg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // ǰ�ò������ų���̬��Դ
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        if (servletPath.contains(".")) {
            String extName = servletPath.substring(servletPath.lastIndexOf("."));

            if (staticResourceExtNameSet.contains(extName)) {

                // �����⵽��ǰ����ȷʵ�Ǿ�̬��Դ����ֱ�ӷ��У������������
                filterChain.doFilter(servletRequest, servletResponse);

                // ��ǰ������������
                return ;
            }

        }

        Connection connection = null;

        try{

            // 1����ȡ���ݿ�����
            connection = JDBCUtils.getConnection();

            // ��Ҫ�������ر��Զ��ύ����
            connection.setAutoCommit(false);

            // 2�����Ĳ���
            filterChain.doFilter(servletRequest, servletResponse);

            // 3���ύ����
            connection.commit();

        }catch (Exception e) {

            try {
                // 4���ع�����
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // ҳ����ʾ�������ﲶ�񵽵��쳣���͵�ָ��ҳ����ʾ
            // ��ȡ�쳣��Ϣ
            String message = e.getMessage();

            // ���쳣��Ϣ����������
            request.setAttribute("systemMessage", message);

            // ������ת����ָ��ҳ��
            request.getRequestDispatcher("/").forward(request, servletResponse);

        }finally {

            // 5���ͷ����ݿ�����
            JDBCUtils.releaseConnection(connection);

        }

    }



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}
