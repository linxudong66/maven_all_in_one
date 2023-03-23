package com.maven.court.dao.impl;

import com.maven.court.dao.BaseDao;
import com.maven.court.dao.api.EmpDao;
import com.maven.court.entity.Emp;

public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao {
    @Override
    public Emp selectEmpByLoginAccount(String loginAccount, String encodedLoginPassword) {

        // 1����д SQL ���
        String sql = "select emp_id empId," +
                "emp_name empName," +
                "emp_position empPosition," +
                "login_account loginAccount," +
                "login_password loginPassword " +
                "from t_emp where login_account=? and login_password=?";

        // 2�����ø��෽����ѯ��������
        return super.getSingleBean(sql, Emp.class, loginAccount, encodedLoginPassword);
    }
}
