package com.maven.court.filter;

import com.maven.court.util.ImperialCourtConst;
import com.sun.net.httpserver.Request;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author LXD
 */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //��ȡ Httpsession ����
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        // �� session ���ȡ��¼����
        Object loginEmp = session.getAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME);

        //�ж� loginEmp �Ƿ�Ϊ��
        if(loginEmp !=null){
            //����Ϊ�գ���˵���ѵ�¼��ֱ�ӷ���
            filterChain.doFilter(request,servletResponse);
            return;
        }
        //��Ϊ�գ�����ʾ������Ϣ�����ص�¼ҳ��
        request.setAttribute("systemMessage",ImperialCourtConst.ACCESS_DENIED_MESSAGE);
        request.getRequestDispatcher("/").forward(request,servletResponse);
    }

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
