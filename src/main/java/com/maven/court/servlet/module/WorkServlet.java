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


    protected void showMemorialsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1�������������ȡ memorialsId
        String memorialsId = request.getParameter("memorialsId");

        //2������ memorialsId �� Service �в�ѯ Memorials ����
        Memorials memorials = memorialsService.gatAllMemorialsDetailById(memorialsId);

        // **********************���书��**********************
        // ��ȡ��ǰ���۶����״̬
        Integer memorialsStatus = memorials.getMemorialsStatus();

        // �ж�����״̬
        if (memorialsStatus == 0) {
            // ��������״̬�����ݿ��޸�
            memorialsService.updateMemorialsStatusToRead(memorialsId);

            // ��������״̬����ǰ�����޸�
            memorials.setMemorialsStatus(1);
        }
        // **********************���书��**********************

        //3���� memorials �������������
        request.setAttribute("memorials",memorials);

        //4��������Ⱦ��ͼ
        String templateName = "memorials-detail";
        processTemplate(templateName,request,response);

    }


    protected void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡ���ύ���������
        String memorialsId = request.getParameter("memorialsId");
        String feedbackContent = request.getParameter("feedBackContent");
        //ִ�и���
        memorialsService.updateMemorialsFeedBack(memorialsId,feedbackContent);

        //�ض�����ʾ���۱�ҳ��
        response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");

    }
}
