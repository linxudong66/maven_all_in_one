package com.maven.court.servlet.module;

import com.maven.court.entity.Emp;
import com.maven.court.exception.LoginFailedException;
import com.maven.court.service.api.EmpService;
import com.maven.court.service.impl.EmpServiceImpl;
import com.maven.court.servlet.base.ModelBaseServlet;
import com.maven.court.util.ImperialCourtConst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author LXD
 */
public class AuthServlet extends ModelBaseServlet {

    private EmpService empService = new EmpServiceImpl();

    protected void login(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1����ȡ�������
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");

            // 2������ EmpService ����ִ�е�¼�߼�
            Emp emp = empService.getEmpByLoginAccount(loginAccount, loginPassword);

            // 3��ͨ�� request ��ȡ HttpSession ����
            HttpSession session = request.getSession();

            // 4������ѯ���� Emp ������� Session ��
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);

            // 5��ǰ��ָ��ҳ����ͼ

            /*
             ǰ����ʱ��ҳ��
             String templateName = "temp";
            processTemplate(templateName, request, response);
              */
            //ǰ����ʽ��Ŀ���ַ
            response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");


        } catch (Exception e) {
            e.printStackTrace();

            // 6���жϴ˴����񵽵��쳣�Ƿ��ǵ�¼ʧ���쳣
            if (e instanceof LoginFailedException) {
                // 7������ǵ�¼ʧ���쳣����ת�ص�¼ҳ��
                // �ٽ��쳣��Ϣ����������
                request.setAttribute("message", e.getMessage());

                // �ڴ�����ͼ��index
                processTemplate("index", request, response);

            }else {
                // 8��������ǵ�¼�쳣���װΪ����ʱ�쳣�����׳�
                throw new RuntimeException(e);

            }

        }

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1��ͨ�� request �����ȡ HttpSession ����
        HttpSession session = request.getSession();

        // 2���� HttpSession ����ǿ��ʧЧ
        session.invalidate();

        // 3���ص���ҳ
        String templateName = "index";
        processTemplate(templateName, request, response);
    }
}