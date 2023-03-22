package com.maven.court.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class ModelBaseServlet extends ViewBaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // ��doGet()�����е���doPost()�����������Ϳ�����doPost()�����м��д�����������
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.������request.getParameter()ǰ�����ý�����������ַ���
        request.setCharacterEncoding("UTF-8");

        // 2.����������л�ȡmethod��Ӧ������
        String method = request.getParameter("method");

        // 3.ͨ���������method��Ӧ�ķ���
        // �ٻ�ȡClass����
        Class<? extends ModelBaseServlet> clazz = this.getClass();

        try {
            // �ڻ�ȡmethod��Ӧ��Method����
            Method methodObject = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

            // �۴򿪷���Ȩ��
            methodObject.setAccessible(true);

            // ��ͨ��Method�������Ŀ�귽��
            methodObject.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();

            // ��Ҫ���ѣ�Ϊ����� TransactionFilter ʵ��������ƣ�������쳣�����׳���
            throw new RuntimeException(e);
        }
    }

}