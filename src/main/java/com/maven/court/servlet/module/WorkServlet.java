package com.maven.court.servlet.module;

import com.maven.court.entity.Memorials;
import com.maven.court.service.api.MemorialsService;
import com.maven.court.service.impl.MemorialsServiceImpl;
import com.maven.court.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class WorkServlet extends ModelBaseServlet {
     private MemorialsService memorialsService = new MemorialsServiceImpl();


    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1������ Service ������ѯ����
       List<Memorials> memorialsList =  memorialsService.gatAllMemorialsDigest();
        //2������ѯ�õ������ݴ���������
        request.setAttribute("memorialsList",memorialsList);
        //3����Ⱦ��ͼ
        String templateName = "memorials-list";
        processTemplate(templateName,request,response);
    }
}
