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
        //获取 Httpsession 对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        // 从 session 域获取登录对象
        Object loginEmp = session.getAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME);

        //判断 loginEmp 是否为空
        if(loginEmp !=null){
            //若不为空，则说明已登录，直接放行
            filterChain.doFilter(request,servletResponse);
            return;
        }
        //若为空，则显示错误信息，返回登录页面
        request.setAttribute("systemMessage",ImperialCourtConst.ACCESS_DENIED_MESSAGE);
        request.getRequestDispatcher("/").forward(request,servletResponse);
    }

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
