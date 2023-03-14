package com.maven.court.servlet.base;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ������ͼģ���ļ��� Servlet ����
 */
public class ViewBaseServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {

        // 1.��ȡServletContext����
        ServletContext servletContext = this.getServletContext();

        // 2.����Thymeleaf����������
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // 3.���������������ò���
        // ��HTML��Ĭ��ģʽ����ȷ������Ϊ�˴�����������
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // ������ǰ׺
        String viewPrefix = servletContext.getInitParameter("view-prefix");

        templateResolver.setPrefix(viewPrefix);

        // �����ú�׺
        String viewSuffix = servletContext.getInitParameter("view-suffix");

        templateResolver.setSuffix(viewSuffix);

        // �����û������ʱ�䣨���룩
        templateResolver.setCacheTTLMs(60000L);

        // �������Ƿ񻺴�
        templateResolver.setCacheable(true);

        // �����÷������˱��뷽ʽ
        templateResolver.setCharacterEncoding("utf-8");

        // 4.����ģ���������
        templateEngine = new TemplateEngine();

        // 5.��ģ�������������ģ�������
        templateEngine.setTemplateResolver(templateResolver);

    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.������Ӧ���������ͺ��ַ���
        resp.setContentType("text/html;charset=UTF-8");

        // 2.����WebContext����
        WebContext webContext = new WebContext(req, resp, getServletContext());

        // 3.����ģ������
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}
