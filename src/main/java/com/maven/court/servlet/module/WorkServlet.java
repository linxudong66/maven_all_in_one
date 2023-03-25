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
        //1、调用 Service 方法查询数据
       List<Memorials> memorialsList =  memorialsService.gatAllMemorialsDigest();
        //2、将查询得到的数据存入请求域
        request.setAttribute("memorialsList",memorialsList);
        //3、渲染视图
        String templateName = "memorials-list";
        processTemplate(templateName,request,response);
    }
}
