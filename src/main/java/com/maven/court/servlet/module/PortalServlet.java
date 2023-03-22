package com.maven.court.servlet.module;

import com.maven.court.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PortalServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ����Ҫ���ʵ���ҳ���߼���ͼ
        String templateName = "index";

        // ���ø���ķ��������߼���ͼ������Ⱦ��ͼ
        processTemplate(templateName, req, resp);
    }
}
