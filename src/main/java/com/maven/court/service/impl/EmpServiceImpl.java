package com.maven.court.service.impl;

import com.maven.court.dao.api.EmpDao;
import com.maven.court.dao.impl.EmpDaoImpl;
import com.maven.court.entity.Emp;
import com.maven.court.exception.LoginFailedException;
import com.maven.court.service.api.EmpService;
import com.maven.court.util.ImperialCourtConst;
import com.maven.court.util.MD5Util;

public class EmpServiceImpl implements EmpService {

    private EmpDao empDao = new EmpDaoImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {

        // 1��������ִ�м���
        String encodedLoginPassword = MD5Util.encode(loginPassword);

        // 2�������˻��ͼ��������ѯ���ݿ�
        Emp emp = empDao.selectEmpByLoginAccount(loginAccount, encodedLoginPassword);

        // 3����� Emp �����Ƿ�Ϊ null
        if (emp != null) {
            //	�ٲ�Ϊ null������ Emp
            return emp;
        } else {
            //	��Ϊ null���׵�¼ʧ���쳣
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
    }
}
